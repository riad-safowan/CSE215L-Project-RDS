package src.com.nsu.rds.ui;

import src.com.nsu.rds.data.models.Courses;
import src.com.nsu.rds.data.models.Student;
import src.com.nsu.rds.data.models.User;
import src.com.nsu.rds.data.repositories.CourseRepository;
import src.com.nsu.rds.data.repositories.StudentRepository;
import src.com.nsu.rds.data.repositories.AdminRepository;
import src.com.nsu.rds.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AdminUI {
    static Scanner scanner = new Scanner(System.in);
    static User currentUser;

    static void homeScreen(User user) {
        currentUser = user;
        Utils.printTitle("ADMIN PANEL");
        boolean isDone = false;

        do {
            System.out.println(" 1 -> See student list");
            System.out.println(" 2 -> Add a new student");
            System.out.println(" 3 -> Remove a student");
            System.out.println(" 4 -> Edit a student data");
            System.out.println(" 5 -> See admin list");
            System.out.println(" 6 -> Add a new admin");
            System.out.println(" 7 -> See course list");
            System.out.println(" 8 -> Add a new course");
            System.out.println(" 9 -> Remove a Course");
            System.out.println("10 -> All user list");
            System.out.println(" 0 -> Logout");

            System.out.print("Select an option: ");
            switch (scanner.nextInt()) {
                case 1 -> studentList();
                case 2 -> addStudent();
                case 3 -> removeStudent();
                case 4 -> editStudent();
                case 5 -> adminList();
                case 6 -> addAdmin();
                case 7 -> courseList();
                case 8 -> addCourse();
                case 9 -> removeCourse();
                case 10 -> allUserList();
                case 0 -> isDone = true;
                default -> System.out.println("Wrong Input! Select Again: ");
            }
        } while (!isDone);
        System.out.println("You are logged out!! ");
        LoginUI.showLoginScreen();
    }

    private static void allUserList() {
        Utils.printTitle("ALL USERS");
        ArrayList<User> list = AdminRepository.getUsers();
        System.out.println("┌──────────┬──────────────────────────────┬────────────┬───────────┐");
        System.out.println("│ ID       │        Name                  │ Status     │ Added by  │");
        System.out.println("├──────────┼──────────────────────────────┼────────────┼───────────┤");
        for (User s : list) {
            System.out.println("│ " + String.format("%-9s", s.getUserId()) + "│ "+ String.format("%-29s", s.getFullName()) + "│ "
                    + String.format("%-11s", (s.isAdmin() ? "ADMIN" : "STUDENT")) + "│ " + String.format("%-9s", s.getAddedBy())+" │");
        }
        System.out.println("└──────────┴──────────────────────────────┴────────────┴───────────┘");
        scanner = new Scanner(System.in);
        System.out.print("Press 'Enter' to go back ");
        scanner.nextLine();
        System.out.println();
    }

    private static void removeCourse() {
        Utils.printTitle("ALL COURSES");
        ArrayList<Courses> cList = CourseRepository.getCourses();
        System.out.println("┌─────┬──────────┬───────────────────────────────┬────────────┐");
        System.out.println("│  No │ Initial  │        Name                   │   Credit   │");
        System.out.println("├─────┼──────────┼───────────────────────────────┼────────────┤");
        for (int i = 0; i < cList.size(); i++) {
            System.out.println("│ " + String.format("%3d",(i + 1))+ " │ " + String.format("%-9s", cList.get(i).getInitial()) + "│ "
                    + String.format("%-30s", cList.get(i).getName()) + "│ " + String.format("%7.2f", cList.get(i).getCredit())+ "    │");
        }
        System.out.println("└─────┴──────────┴───────────────────────────────┴────────────┘");
        System.out.print("Enter Course No to remove (0 to back): ");
        int no = scanner.nextInt();
        if (no == 0) System.out.println();
        else if (no > cList.size()) System.out.println("Invalid Input!!\n");
        else {
            String initial = cList.get(no - 1).getInitial();
            CourseRepository.removeCourse(initial);
            System.out.println("### A new course removed ###\n");
            System.out.println();
        }
    }

    private static void addCourse() {
        Utils.printTitle("ADD COURSE");
        boolean isSuccess = false;
        do {
            System.out.print("Enter Course initial (0 to back): ");
            String initial = scanner.next();
            if (Objects.equals(initial, "0")) break;
            System.out.print("Enter Course Name: ");
            String name = scanner.next();
            System.out.print("Enter Course credit: ");
            int credit = scanner.nextInt();
            Courses courses = new Courses(initial, name, credit);
            try {
                CourseRepository.addCourse(courses);
                isSuccess = true;
                System.out.println("### A new course added ###\n");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!isSuccess);
    }

    private static void courseList() {
        Utils.printTitle("ALL COURSES");
        ArrayList<Courses> list = CourseRepository.getCourses();
        System.out.println("┌──────────┬───────────────────────────────┬────────────┐");
        System.out.println("│ Initial  │         Course Name           │   Credit   │");
        System.out.println("├──────────┼───────────────────────────────┼────────────┤");
        for (Courses c : list) {
            System.out.println("│ " + String.format("%-9s", c.getInitial()) + "│ " + String.format("%-30s", c.getName())
                    + "│ "  +String.format("%7.2f", c.getCredit()) + "    │");
        }
        System.out.println("└──────────┴───────────────────────────────┴────────────┘");
        scanner = new Scanner(System.in);
        System.out.print("\nPress 'Enter' to go back ");
        scanner.nextLine();
        System.out.println();
    }

    private static void addAdmin() {
        Utils.printTitle("ADMIN FORM");
        boolean isSuccess = false;
        do {
            System.out.print("Enter ADMIN ID (0 to back): ");
            String userId = scanner.next();
            if (Objects.equals(userId, "0")) break;
            System.out.print("Enter First Name: ");
            String firstName = scanner.next();
            System.out.print("Enter Last Name: ");
            String lastName = scanner.next();
            System.out.print("Set a password: ");
            String password = scanner.next();
            System.out.print("Re-enter the password: ");
            String rePassword = scanner.next();
            if (!Objects.equals(password, rePassword)) {
                System.out.println("Password did not match!!\n");
                continue;
            }

            User user = new User(userId, password, true, currentUser.getUserId(), firstName, lastName);
            try {
                AdminRepository.addUser(user);
                isSuccess = true;
                System.out.println("### A new admin added ###\n");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!isSuccess);
    }

    private static void adminList() {
        Utils.printTitle("ALL ADMINS");
        ArrayList<User> list = AdminRepository.getAdmins();
        System.out.println("┌──────────┬───────────────────────────────┐");
        System.out.println("│ ID       │        Name                   │");
        System.out.println("├──────────┼───────────────────────────────┤");
        for (User s : list) {
            System.out.println("│ " + String.format("%-9s", s.getUserId()) + "│ " + String.format("%-29s", s.getFullName())+" │" );
        }
        System.out.println("└──────────┴───────────────────────────────┘");
        scanner = new Scanner(System.in);
        System.out.print("Press 'Enter' to go back ");
        scanner.nextLine();
    }

    private static void editStudent() {
    }

    private static void removeStudent() {
        Utils.printTitle("REMOVE A STUDENT");
        ArrayList<Student> list = StudentRepository.getStudents();
        System.out.println("┌──────────┬──────────────────────────────┬────────────────┐");
        System.out.println("│   ID     │       Student Name           │   Due amount   │");
        System.out.println("├──────────┼──────────────────────────────┼────────────────┤");
        for (Student student : list) {
            System.out.println("│ " + String.format("%-9s", student.getUserId()) + "│ "
                    + String.format("%-29s", student.getFullName()) +  "│ BDT" + String.format("%-11.1f",student.getUnpaidAmount()) +" │");
        }
        System.out.println("└──────────┴──────────────────────────────┴────────────────┘");
        System.out.print("Enter Student ID to remove(0 to back): ");
        scanner = new Scanner(System.in);
        String id = scanner.next();
        if (Objects.equals(id, "0")) {
            System.out.println();
            return;
        }
        if (StudentRepository.removeStudent(id))
            System.out.println("### A student is removed ###\n");
        else System.out.println("Student not found with id " + id);
        System.out.println();

    }

    private static void addStudent() {
        Utils.printTitle("STUDENT FORM");
        boolean isSuccess = false;
        do {
            System.out.print("Enter NSU ID (0 to back): ");
            String userId = scanner.next();
            if (Objects.equals(userId, "0")) {
                System.out.println();
                break;
            }
            System.out.print("Enter First Name: ");
            String firstName = scanner.next();
            System.out.print("Enter Last Name: ");
            String lastName = scanner.next();
            System.out.print("Set a password: ");
            String password = scanner.next();
            System.out.print("Re-enter the password: ");
            String rePassword = scanner.next();
            if (!Objects.equals(password, rePassword)) {
                System.out.println("Password did not match!!\n");
                continue;
            }

            Student student = new Student(userId, password, false, currentUser.getUserId(), firstName, lastName, List.of(), 0.0);
            try {
                StudentRepository.addStudent(student);
                isSuccess = true;
                System.out.println("### A new student added ###\n");
                System.out.println();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!isSuccess);
    }

    private static void studentList() {
        Utils.printTitle("ALL STUDENTS");
        ArrayList<Student> list = StudentRepository.getStudents();
        System.out.println("┌──────────┬──────────────────────────────┬────────────────┐");
        System.out.println("│    ID    │         Course Name          │   Due amount   │");
        System.out.println("├──────────┼──────────────────────────────┼────────────────┤");
        for (Student s : list) {
            System.out.println("│ " + String.format("%-9s", s.getUserId()) +
                    "│ " + String.format("%-29s", s.getFullName()) +
                    "│ BDT" + String.format("%-11.1f", s.getUnpaidAmount()) + " │");
        }
        System.out.println("└──────────┴──────────────────────────────┴────────────────┘");
        scanner = new Scanner(System.in);
        System.out.print("Press 'Enter' to go back ");
        scanner.nextLine();
        System.out.println();
    }
}


