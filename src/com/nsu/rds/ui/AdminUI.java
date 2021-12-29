package src.com.nsu.rds.ui;

import src.com.nsu.rds.data.repositories.StudentRepository;
import src.com.nsu.rds.models.Student;
import src.com.nsu.rds.models.User;

import java.util.Scanner;

public class AdminUI {
    static Scanner scanner = new Scanner(System.in);
    static StudentRepository studentRepository = new StudentRepository();
    static void homeScreen(User user) {
        System.out.println("Welcome " + user.getUsername());
        boolean isDone=false;
        do{
            System.out.println("1. Add a new student");
            System.out.println("0. Logout");
           switch (scanner.nextInt()){
               case 1 -> addANewStudent(user);
               case 0 -> isDone =true;
           }
        }while (!isDone);
        LoginUI.showLoginScreen();
    }

    private static void addANewStudent(User user) {
//        studentRepository.addStudent(new Student());
    }
}


