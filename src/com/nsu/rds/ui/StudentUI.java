package src.com.nsu.rds.ui;

import src.com.nsu.rds.data.models.Student;
import src.com.nsu.rds.data.models.User;
import src.com.nsu.rds.data.repositories.StudentRepository;
import src.com.nsu.rds.utils.Utils;

import java.util.Objects;
import java.util.Scanner;

public class StudentUI {
    static Scanner scanner = new Scanner(System.in);
    static Student currentUser;

    static void homeScreen(User user) {
        currentUser = StudentRepository.getStudentById(user.getUserId());
        Utils.printTitle(currentUser.getFullName());

        boolean isDone = false;
        do {
            System.out.println("1 -> See your information");
            System.out.println("2 -> Advising window");
            System.out.println("3 -> Change Password");
            System.out.println("0 -> Logout");

            try {
                System.out.print("Select an option: ");
                scanner = new Scanner(System.in);
                switch (scanner.nextInt()) {
                    case 1 -> studentInfo();
                    case 2 -> advisingWindow();
                    case 3 -> changePassword();
                    case 0 -> isDone = true;
                    default -> System.out.println("Wrong Input! Select Again: \n");
                }
            } catch (Exception e) {
                System.out.println("Wrong Input! Select Again: \n");
            }
        } while (!isDone);
        System.out.println("You are logged out!! ");
        LoginUI.showLoginScreen();
    }

    private static void changePassword() {
        Utils.printTitle("CHANGE PASSWORD");
        boolean isSuccess = false;
        do {
            System.out.print("Enter your old Password (0 to cancel): ");
            String oldP = scanner.next();
            if (Objects.equals(oldP, "0")) {
                System.out.println();
                break;
            }
            System.out.print("Enter your new password: ");
            String newP = scanner.next();
            System.out.print("Re-enter your new password: ");
            String rNewP = scanner.next();
            if (!Objects.equals(oldP, currentUser.getPassword()) || !Objects.equals(newP, rNewP)) {
                System.out.print("Password did not match!!\n");
                continue;
            }
            StudentRepository.updatePassword(currentUser.getUserId(), newP);
            isSuccess = true;
            System.out.println("### Password changed ###\n");
        } while (!isSuccess);
    }

    private static void advisingWindow() {
        AdvisingUI.homeScreen(currentUser);
    }

    private static void studentInfo() {
        Utils.printTitle("YOUR INFO");
        System.out.println("ID     : " + currentUser.getUserId());
        System.out.println("Name   : " + currentUser.getFullName());
        System.out.println("To Pay : " + currentUser.getUnpaidAmount()+ "/- BDT");
        System.out.println();
    }
}
