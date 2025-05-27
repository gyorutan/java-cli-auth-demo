package com.tpi.demo;

import com.tpi.demo.auth.AuthUi;
import com.tpi.demo.auth.JdbcAuthService;

import java.io.PrintStream;
import java.util.Scanner;

/** <em>이 클래스는 메인 클래스입니다.</em>
*/
public class Main {
    public static void main(String[] args) {
        String dbUrl = "jdbc:sqlite:./auth.db";
        Scanner scanner = new Scanner(System.in);
        PrintStream printStream = System.out;
        JdbcAuthService db = new JdbcAuthService(dbUrl);

        AuthUi authUi = new AuthUi(scanner, printStream, db);

        authUi.registerInput();

        authUi.loginInput();
    }
}