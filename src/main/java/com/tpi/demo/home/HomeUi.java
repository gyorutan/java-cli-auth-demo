package com.tpi.demo.home;

import com.tpi.demo.auth.AuthUi;

import java.io.PrintStream;
import java.util.Scanner;

public class HomeUi {
    private final Scanner scanner;
    private final PrintStream printStream;
    private final AuthUi authUi;
    private String path = "/home";

    public HomeUi(Scanner scanner, PrintStream printStream, AuthUi authUi) {
        this.scanner = scanner;
        this.printStream = printStream;
        this.authUi = authUi;
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
                case "/quit":
                    printStream.println("Exiting the program");
                    return;
                default:
                    path = "/home";
            }
        }
    }

    private void home() {
        printStream.print(
                "\n" +
                "---------------------------------  \n" +
                " WELCOME TO MY AUTH CLI TUTORIAL   \n" +
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
    }
}
