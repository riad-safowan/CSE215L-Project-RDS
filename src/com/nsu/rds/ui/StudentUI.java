package src.com.nsu.rds.ui;

import src.com.nsu.rds.data.models.User;

import java.util.Scanner;

public class StudentUI {
    static Scanner scanner = new Scanner(System.in);
    static User currentUser;

    static void homeScreen(User user) {

        currentUser = user;
        System.out.println("Welcome " + user.getUsername());
        boolean isDone = false;

        System.out.print("Select an option: ");
        do {
            System.out.println("1. See your information");
            System.out.println("2. Advising window");

            System.out.println("0. Logout");

            switch (scanner.nextInt()) {
                case 1 -> studentInfo();
                case 2 -> advisingWindow();
                case 0 -> isDone = true;
                default -> System.out.println("Wrong Input! Select Again: ");
            }
        } while (!isDone);
        System.out.println("You are logged out\n\n\n\n\n");
        LoginUI.showLoginScreen();
    }

    private static void advisingWindow() {
    }

    private static void studentInfo() {
    }
}
