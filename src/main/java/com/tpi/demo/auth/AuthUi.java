package com.tpi.demo.auth;

import com.tpi.demo.util.ThreadUtils;

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
        printStream.println("Starting registration.");
        printStream.print("username : ");
        String username = scanner.nextLine();
        printStream.print("password : ");
        String password = scanner.nextLine();

        User user = new User(username, password);

        auth.register(user);

        printStream.println("Registration complete");

        ThreadUtils.sleep(1000);
    }

    public void loginInput() {
        printStream.println("Starting login.");
        printStream.print("username : ");
        String username = scanner.nextLine();
        printStream.print("password : ");
        String password = scanner.nextLine();

        User user = new User(username, password);

        try {
            auth.login(user);
        } catch (Exception e) {
            printStream.println(e.getMessage());
            return;
        }

        printStream.println("Welcome to my project, " + user.getUsername());

        ThreadUtils.sleep(1000);
    }

    public void logout() {
       auth.logoutUser();
       printStream.println("user logged out successfully");

       ThreadUtils.sleep(1000);
    }
}
