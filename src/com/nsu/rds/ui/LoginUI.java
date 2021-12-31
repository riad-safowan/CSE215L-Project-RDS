package src.com.nsu.rds.ui;

import src.com.nsu.rds.data.repositories.UserRepository;
import src.com.nsu.rds.data.models.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LoginUI {
    static Scanner scanner = new Scanner(System.in);
    static UserRepository userRepository = new UserRepository();

    public static void showLoginScreen() {
        UI.showWelcome();
        System.out.println("Login");
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();

        User user = loginSuccess(username, password);
        if (!user.getUsername().isEmpty()) {
            if (user.isAdmin()) {
                AdminUI.homeScreen(user);
            } else {
                StudentUI.homeScreen(user);
            }
        }
    }

    private static User loginSuccess(String username, String password) {
        ArrayList<User> users = userRepository.getUsers();
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username)) {
                if (Objects.equals(user.getPassword(), password)) return user;
            }
        }
        return new User();
    }
}
