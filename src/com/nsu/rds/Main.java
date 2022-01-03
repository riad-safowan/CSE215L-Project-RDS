package src.com.nsu.rds;

import src.com.nsu.rds.data.repositories.CourseRepository;
import src.com.nsu.rds.data.repositories.StudentRepository;
import src.com.nsu.rds.data.repositories.UserRepository;
import src.com.nsu.rds.ui.LoginUI;

public class Main {

    public static void main(String[] args) {

        UserRepository.init();
        StudentRepository.init();
        CourseRepository.init();

        LoginUI.showLoginScreen();
    }
}
