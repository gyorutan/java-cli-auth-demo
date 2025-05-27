package com.tpi.demo.auth;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
