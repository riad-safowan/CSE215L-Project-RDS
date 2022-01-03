package src.com.nsu.rds.ui;

import src.com.nsu.rds.data.models.Student;
import src.com.nsu.rds.data.models.User;
import src.com.nsu.rds.data.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminUI {
    static Scanner scanner = new Scanner(System.in);
    static User currentUser;

    static void homeScreen(User user) {
        currentUser = user;
        System.out.println("Welcome " + user.getFullName());
        boolean isDone = false;

        do {
            System.out.println("1. See student list");
            System.out.println("2. Add a new student");
            System.out.println("3. Remove a student");
            System.out.println("4. Edit a student data");
            System.out.println("5. See admin list");
            System.out.println("6. Add a new admin");
            System.out.println("7. See course list");
            System.out.println("8. Booked course list");
            System.out.println("9. Add a new course");
            System.out.println("10. Remove a Course");
            System.out.println("0. Logout");

            System.out.print("Select an option: ");
            switch (scanner.nextInt()) {
                case 1 -> studentList();
                case 2 -> addStudent();
                case 3 -> removeStudent();
                case 4 -> editStudent();
                case 5 -> adminList();
                case 6 -> addAdmin();
                case 7 -> courseList();
                case 8 -> bookedCoursesData();
                case 9 -> addCourse();
                case 10 -> removeCourse();
                case 0 -> isDone = true;
                default -> System.out.println("Wrong Input! Select Again: ");
            }
        } while (!isDone);
        System.out.println("You are logged out\n\n\n\n\n");
        LoginUI.showLoginScreen();
    }

    private static void removeCourse() {
    }

    private static void addCourse() {
    }

    private static void bookedCoursesData() {
    }

    private static void courseList() {
    }

    private static void addAdmin() {
    }

    private static void adminList() {
    }

    private static void editStudent() {
    }

    private static void removeStudent() {
    }

    private static void addStudent() {
        System.out.print("Enter First Name: ");
        String firstName = scanner.next();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.next();
        System.out.print("Set a password: ");
        String password = scanner.next();
//        System.out.println("Re-enter the password: ");
//        String rePassword = scanner.next();

        Student student = new Student(firstName + "." + lastName, password, false, currentUser.getUsername(), firstName, lastName, List.of(), 0.0);
        StudentRepository.addStudent(student);
    }

    private static void studentList() {
        ArrayList<Student> list = StudentRepository.getStudents();
        for (Student s : list) {
            System.out.println("Name: " + s.getFullName());
        }

//        scanner.reset();
//        scanner.close();
//        scanner.remove();
        scanner = new Scanner(System.in);
        System.out.print("Press enter to go back ");
        scanner.nextLine();
    }

}


