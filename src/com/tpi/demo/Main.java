package com.tpi.demo;

import com.tpi.demo.auth.Auth;
import com.tpi.demo.auth.AuthUi;

import java.io.PrintStream;
import java.util.Scanner;

/** <em>이 클래스는 메인 클래스입니다.</em>
*/
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintStream printStream = System.out;
        Auth auth = new Auth();
        AuthUi authUi = new AuthUi(scanner, printStream, auth);

        authUi.registerInput();

        authUi.loginInput();
    }
}