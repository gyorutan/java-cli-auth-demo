package com.tpi.demo.auth;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcAuthService implements AuthService {
    // DB 연결
    private static final String DB_URL = "jdbc:sqlite:./auth.db";

//  static {} <- 나중에 공부

    public JdbcAuthService() {
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

        try (Connection conn = DriverManager.getConnection(DB_URL); // DB 연결
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql); // SQL 실행 (테이블 생성)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
