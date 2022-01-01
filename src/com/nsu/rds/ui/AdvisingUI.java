package src.com.nsu.rds.ui;

import src.com.nsu.rds.data.models.User;

import java.util.Scanner;

public class AdvisingUI {
    static Scanner scanner = new Scanner(System.in);
    static User currentUser;

    static void homeScreen(User user) {

        currentUser = user;
        System.out.println("Welcome " + user.getUsername());
        boolean isDone = false;

        do {
            System.out.println("1. See offered course list");
            System.out.println("2. Add a new course");
            System.out.println("3. Remove a course");
            System.out.println("0. Back");

            System.out.print("Select an option: ");
            switch (scanner.nextInt()) {
                case 1 -> offeredCourseList();
                case 2 -> addCourse();
                case 3 -> removeCourse();
                case 0 -> isDone = true;
                default -> System.out.println("Wrong Input! Select Again: ");
            }
        } while (!isDone);
        LoginUI.showLoginScreen();
    }

    private static void removeCourse() {
    }

    private static void addCourse() {
    }

    private static void offeredCourseList() {
    }
}
