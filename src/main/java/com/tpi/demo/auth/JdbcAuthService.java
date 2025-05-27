package com.tpi.demo.auth;


import java.sql.*;

public class JdbcAuthService implements AuthService {
    // DB 연결
    private final String dbUrl;

//  static {} <- 나중에 공부

    public JdbcAuthService(String dbUrl) {
        this.dbUrl = dbUrl;
        createTable();
    }

    @Override
    public void register(User user) {
        String sql = "INSERT INTO users(username, password)" +
                " VALUES(?,?)";
        try (Connection conn = DriverManager.getConnection(dbUrl); // DB 연결
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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
