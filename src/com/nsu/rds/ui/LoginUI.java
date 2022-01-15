package com.nsu.rds.ui;

import com.nsu.rds.data.repositories.AdminRepository;
import com.nsu.rds.data.models.User;
import com.nsu.rds.utils.Utils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LoginUI {
    static Scanner scanner = new Scanner(System.in);
    public static void showLoginScreen() {
        boolean isSuccess = false;
        do {
            Utils.printTitle("LOGIN");
            System.out.print("Enter UserID: ");
            String userId = scanner.next();
            System.out.print("Enter Password: ");
            String password = scanner.next();

            User user = loginSuccess(userId, password);
            if (user.getUserId() != null && !user.getUserId().isEmpty()) {
                isSuccess = true;
                if (user.isAdmin()) {
                    AdminUI.homeScreen(user);
                } else {
                    StudentUI.homeScreen(user);
                }
            } else {
                System.out.println("UserID or password is incorrect! ");
            }
        } while (!isSuccess);
    }

    private static User loginSuccess(String username, String password) {
        ArrayList<User> users = AdminRepository.getUsers();
        for (User user : users) {
            if (Objects.equals(user.getUserId(), username)) {
                if (Objects.equals(user.getPassword(), password)) return user;
            }
        }
        return new User();
    }
}
