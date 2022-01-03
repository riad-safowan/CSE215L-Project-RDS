package src.com.nsu.rds;

import src.com.nsu.rds.data.repositories.CourseRepository;
import src.com.nsu.rds.data.repositories.StudentRepository;
import src.com.nsu.rds.data.repositories.UserRepository;
import src.com.nsu.rds.data.models.Courses;
import src.com.nsu.rds.data.models.User;
import src.com.nsu.rds.ui.LoginUI;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        UserRepository.init();
        StudentRepository.init();
        CourseRepository.init();

        LoginUI.showLoginScreen();
    }
}
