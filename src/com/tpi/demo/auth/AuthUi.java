package com.tpi.demo.auth;

import java.io.PrintStream;
import java.util.Scanner;

/** <em>이 클래스는 AUTH UI 클래스입니다.</em>
 */
public class AuthUi {

    private final Scanner scanner;
    private final PrintStream printStream;
    private final AuthService auth;

    public AuthUi(Scanner scanner, PrintStream printStream, AuthService auth) {
        this.scanner = scanner;
        this.printStream = printStream;
        this.auth = auth;
    }

    public void registerInput() {
        printStream.println("회원가입을 진행합니다");
        printStream.print("유저네임 : ");
        String username = scanner.nextLine();
        printStream.print("비밀번호 : ");
        String password = scanner.nextLine();

        User user = new User(username, password);

        auth.register(user);
    }

    public void loginInput() {
        printStream.println("로그인을 진행합니다");
        printStream.print("유저네임 : ");
        String username = scanner.nextLine();
        printStream.print("비밀번호 : ");
        String password = scanner.nextLine();

        User user = new User(username, password);

        try {
            auth.login(user);
        } catch (Exception e) {
            printStream.println(e.getMessage());
            return;
        }

        printStream.println(user.getUsername() + "님이 로그인하였습니다");

    }

}
