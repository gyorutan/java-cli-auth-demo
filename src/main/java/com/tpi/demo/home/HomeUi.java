package com.tpi.demo.home;

import com.tpi.demo.auth.AuthService;
import com.tpi.demo.auth.AuthUi;
import com.tpi.demo.auth.User;

import java.io.PrintStream;
import java.util.Scanner;

public class HomeUi {
    private final Scanner scanner;
    private final PrintStream printStream;
    private final AuthUi authUi;
    private final AuthService authService;
    private String path = "/home";

    public HomeUi(Scanner scanner, PrintStream printStream, AuthUi authUi, AuthService authService) {
        this.scanner = scanner;
        this.printStream = printStream;
        this.authUi = authUi;
        this.authService = authService;
    }

    public void run() {
        while (true) {
            // flatten
            switch (path) {
                case "/home":
                    home();
                    break;
                case "/login":
                    authUi.loginInput();
                    path = "/home";
                    break;
                case "/register":
                    authUi.registerInput();
                    path = "/home";
                    break;
                case "/logout":
                    authUi.logout();
                    path = "/home";
                    break;
                case "/quit":
                    printStream.println("Exiting the program");
                    return;
                default:
                    path = "/home";
            }
        }
    }

    private void home() {
        User signedUser = authService.getUserSession();

        if (signedUser == null) {
            printStream.print(
                    "\n" +
                            "---------------------------------  \n" +
                            " WELCOME TO MY AUTH CLI TUTORIAL   \n" +
                            "---------------------------------  \n" +
                            "  Please select a menu option      \n" +
                            "---------------------------------  \n" +
                            "1. LOGIN \n" +
                            "2. REGISTER \n" +
                            "0. CLOSE \n" +
                            "> "
            );

            int number = scanner.nextInt();
            scanner.nextLine();

            switch (number) {
                case 1:
                    path = "/login";
                    break;
                case 2:
                    path = "/register";
                    break;
                case 0:
                    path = "/quit";
                    break;
                default:
                    path = "/home";
                    printStream.println("Please select a valid menu option.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
            }
        } else {
            printStream.print(
                    "\n" +
                            "---------------------------------  \n" +
                            " WELCOME TO MY AUTH CLI TUTORIAL   \n" +
                            "---------------------------------  \n" +
                            "  %s is currently logged in \n".formatted(signedUser.getUsername()) +
                            "  Please select a menu option      \n" +
                            "---------------------------------  \n" +
                            "9. LOGOUT \n" +
                            "0. CLOSE \n" +
                            "> "
            );

            int number = scanner.nextInt();
            scanner.nextLine();

            switch (number) {
                case 9:
                    path = "/logout";
                    break;
                case 0:
                    path = "/quit";
                    break;
                default:
                    path = "/home";
                    printStream.println("Please select a valid menu option.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
            }
        }
    }
}
