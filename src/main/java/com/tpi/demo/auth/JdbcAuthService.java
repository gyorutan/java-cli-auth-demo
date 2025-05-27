package com.tpi.demo.auth;

import java.sql.*;
import java.util.Objects;

public class JdbcAuthService implements AuthService {
    // DB 연결
    private final String dbUrl;
    private User signedUser;

//  static {} <- 나중에 공부

    public JdbcAuthService(String dbUrl) {
        this.dbUrl = dbUrl;
        createTable();
    }

    @Override
    public void register(User user) {
        String sql = "INSERT INTO users(username, password)" +
                " VALUES(?,?)";
        try (
                Connection conn = DriverManager.getConnection(dbUrl); // DB 연결
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            // insert, update, delete 는 executeUpdate()로 실행
            stmt.executeUpdate(); // executeUpdate() 는 행(rows)의 수를 반환함
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(User user) {
        String sql = "SELECT password" +
                " FROM users" +
                " WHERE username=?";
        try (
                Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, user.getUsername());
            try (ResultSet rs = stmt.executeQuery()) {
                // rs.next 를 실행해야 데이터를 다룰 수 있다.
                if (rs.next()) { // 여러 행일 땐 if 대신 while 사용
                    String dbPassword = rs.getString("password");
                    boolean isMatched = Objects.equals(dbPassword, user.getPassword());

                    if (!isMatched) {
                        throw new RuntimeException("Invalid username or password.");
                    }

                    signedUser = user;
                } else {
                    throw new RuntimeException("Invalid username or password.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  username TEXT UNIQUE NOT NULL," +
                "  password TEXT NOT NULL" +
                ");";

        try (Connection conn = DriverManager.getConnection(dbUrl); // DB 연결
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql); // SQL 실행 (테이블 생성)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
