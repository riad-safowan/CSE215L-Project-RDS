package src.com.nsu.rds.ui;

import src.com.nsu.rds.data.Data;
import src.com.nsu.rds.models.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LoginUI {
    static Scanner scanner = new Scanner(System.in);
    static Data data = new Data();

    public static void showLoginScreen() {
        UI.showWelcome();
        System.out.println("Login");
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();

        switch (loginSuccess(username, password)) {
            case "ADMIN" -> {
                System.out.println("logged in as admin");
            }
            case "USER" -> {
                System.out.println("logged in as student");
            }
            default -> {
                System.out.println("Incorrect username or password");
            }
        }

    }

    private static String loginSuccess(String username, String password) {
        ArrayList<User> users = data.getUsers();
        for (User user : users) {
            if (Objects.equals(user.getName(), username)) {
                if (Objects.equals(user.getPassword(), password)) return (user.isAdmin()) ? "ADMIN" : "USER";
            }
        }
        return "";
    }
}
