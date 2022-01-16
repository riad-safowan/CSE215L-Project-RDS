package com.nsu.rds.ui;

import com.nsu.rds.data.models.Courses;
import com.nsu.rds.data.models.Fee;
import com.nsu.rds.data.models.Student;
import com.nsu.rds.data.models.User;
import com.nsu.rds.data.repositories.CourseRepository;
import com.nsu.rds.data.repositories.StudentRepository;
import com.nsu.rds.data.repositories.AdminRepository;
import com.nsu.rds.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AdminUI {
    static Scanner scanner = new Scanner(System.in);
    static User currentUser;

    static void homeScreen(User user) {
        currentUser = user;
        Utils.printTitle("ADMIN PANEL(" + currentUser.getFullName() + ")");
        boolean isDone = false;

        do {
            System.out.println(" 1 -> See student list");
            System.out.println(" 2 -> Add a new student");
            System.out.println(" 3 -> Remove a student");
            System.out.println(" 4 -> Edit a student data");
            System.out.println(" 5 -> Change Password");
            System.out.println(" 6 -> See admin list");
            System.out.println(" 7 -> Add a new admin");
            System.out.println(" 8 -> See course list");
            System.out.println(" 9 -> Add a new course");
            System.out.println("10 -> Remove a Course");
            System.out.println("11 -> All user list");
            System.out.println("12 -> Update all fees");
            System.out.println(" 0 -> Logout");
            try {
                System.out.print("Select an option: ");
                scanner = new Scanner(System.in);
                switch (scanner.nextInt()) {

                    case 1 -> studentList();
                    case 2 -> addStudent();
                    case 3 -> removeStudent();
                    case 4 -> editStudent();
                    case 5 -> changeAdminPass();
                    case 6 -> adminList();
                    case 7 -> addAdmin();
                    case 8 -> courseList();
                    case 9 -> addCourse();
                    case 10 -> removeCourse();
                    case 11 -> allUserList();
                    case 12 -> updateFees();
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

    private static void updateFees() {

        Fee fees = AdminRepository.getFees();
        System.out.print("Update creditFee ('-1' to set unchanged as " + fees.getCreditFee() + ") : ");
        double input = scanner.nextDouble();
        if (!(input == -1.0)) {
            fees.setCreditFee(input);
        }
        System.out.print("Update activityFee ('-1' to set unchanged as " + fees.getActivityFee() + ") : ");
        input = scanner.nextDouble();
        if (!(input == -1.0)) {
            fees.setActivityFee(input);
        }
        System.out.print("Update computerLabFee ('-1' to set unchanged as " + fees.getComputerLabFee() + ") : ");
        input = scanner.nextDouble();
        if (!(input == -1.0)) {
            fees.setComputerLabFee(input);
        }
        System.out.print("Update libraryFee ('-1' to set unchanged as " + fees.getLibraryFee() + ") : ");
        input = scanner.nextDouble();
        if (!(input == -1.0)) {
            fees.setLibraryFee(input);
        }
        System.out.print("Update scienceLabFee ('-1' to set unchanged as " + fees.getScienceLabFee() + ") : ");
        input = scanner.nextDouble();
        if (!(input == -1.0)) {
            fees.setScienceLabFee(input);
        }
        System.out.print("Update waiver ('-1' to set unchanged as " + fees.getWaiver() + ") : ");
        input = scanner.nextDouble();
        if (!(input == -1.0)) {
            fees.setWaiver(input);
        }

        AdminRepository.setFees(fees);

        System.out.println("Done with updating All fees\n");

    }

    private static void allUserList() {
        Utils.printTitle("ALL USERS");
        ArrayList<User> list = AdminRepository.getUsers();
        if (list.size() == 0) {
            System.out.println("User list is empty!\n");
            return;
        }
        System.out.println("┌────────────┬──────────────────────────────┬────────────┬───────────┐");
        System.out.println("│ ID         │        Name                  │ Status     │ Added by  │");
        System.out.println("├────────────┼──────────────────────────────┼────────────┼───────────┤");
        for (User s : list) {
            System.out.println("│ " + String.format("%-11s", s.getUserId()) + "│ " + String.format("%-29s", s.getFullName()) + "│ "
                    + String.format("%-11s", (s.isAdmin() ? "ADMIN" : "STUDENT")) + "│ " + String.format("%-9s", s.getAddedBy()) + " │");
        }
        System.out.println("└────────────┴──────────────────────────────┴────────────┴───────────┘");
        scanner = new Scanner(System.in);
        System.out.print("Press 'Enter' to go back ");
        scanner.nextLine();
        System.out.println();
    }

    private static void removeCourse() {
        Utils.printTitle("ALL COURSES");
        ArrayList<Courses> cList = CourseRepository.getCourses();
        if (cList.size() == 0) {
            System.out.println("Course list is empty!\n");
            return;
        }
        System.out.println("┌─────┬──────────┬───────────────────────────────┬────────────┐");
        System.out.println("│  No │ Initial  │       Course Name             │   Credit   │");
        System.out.println("├─────┼──────────┼───────────────────────────────┼────────────┤");
        for (int i = 0; i < cList.size(); i++) {
            System.out.println("│ " + String.format("%3d", (i + 1)) + " │ " + String.format("%-9s", cList.get(i).getInitial()) + "│ "
                    + String.format("%-30s", cList.get(i).getName()) + "│ " + String.format("%7.2f", cList.get(i).getCredit()) + "    │");
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
            System.out.print("Enter Course Name               : ");
            scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            System.out.print("Enter Course credit             : ");
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
        if (list.size() == 0) {
            System.out.println("Course list is empty!\n");
            return;
        }
        System.out.println("┌──────────┬───────────────────────────────┬────────────┐");
        System.out.println("│ Initial  │         Course Name           │   Credit   │");
        System.out.println("├──────────┼───────────────────────────────┼────────────┤");
        for (Courses c : list) {
            System.out.println("│ " + String.format("%-9s", c.getInitial()) + "│ " + String.format("%-30s", c.getName())
                    + "│ " + String.format("%7.2f", c.getCredit()) + "    │");
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
            System.out.print("Enter First Name          : ");
            scanner = new Scanner(System.in);
            String firstName = scanner.nextLine();
            System.out.print("Enter Last Name           : ");
            scanner = new Scanner(System.in);
            String lastName = scanner.nextLine();
            System.out.print("Set a password            : ");
            String password = scanner.next();
            System.out.print("Re-enter the password     : ");
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
        if (list.size() == 0) {
            System.out.println("No admin available!\n");
            return;
        }
        System.out.println("┌────────────┬───────────────────────────────┐");
        System.out.println("│ ID         │      Admin  Name              │");
        System.out.println("├────────────┼───────────────────────────────┤");
        for (User s : list) {
            System.out.println("│ " + String.format("%-11s", s.getUserId()) + "│ " + String.format("%-29s", s.getFullName()) + " │");
        }
        System.out.println("└────────────┴───────────────────────────────┘");
        scanner = new Scanner(System.in);
        System.out.print("Press 'Enter' to go back ");
        scanner.nextLine();
    }

    private static void editStudent() {
        boolean isDone = false;
        Utils.printTitle("UPDATE STUDENT INFO");
        ArrayList<Student> list = StudentRepository.getStudents();
        if (list.size() == 0) {
            System.out.println("Student list is empty!\n");
            return;
        }
        System.out.println("┌────────────┬──────────────────────────────┬────────────────┐");
        System.out.println("│   ID       │       Student Name           │   Due amount   │");
        System.out.println("├────────────┼──────────────────────────────┼────────────────┤");
        for (Student student : list) {
            System.out.println("│ " + String.format("%-11s", student.getUserId()) + "│ " + String.format("%-29s", student.getFullName()) + "│ " + String.format("%8.2f/- BDT", student.getUnpaidAmount()) + " │");
        }
        System.out.println("└────────────┴──────────────────────────────┴────────────────┘");
        System.out.print("Enter Student ID to update(0 to back): ");
        scanner = new Scanner(System.in);
        String id = scanner.next();
        if (Objects.equals(id, "0")) {
            System.out.println();
            return;
        }
        Student s = StudentRepository.getStudentById(id);

        Utils.printTitle("STUDENT FORM");
        boolean isSuccess = false;
        do {
            System.out.print("Enter First Name   (0 to set unchanged as \"" + s.getFirstName() + "\"): ");
            scanner = new Scanner(System.in);
            String firstName = scanner.nextLine();
            if (!Objects.equals(firstName, "0")) {
                s.setFirstName(firstName);
            }

            System.out.print("Enter Last Name    (0 to set unchanged as \"" + s.getLastName() + "\"): ");
            scanner = new Scanner(System.in);
            String lastName = scanner.nextLine();
            if (!Objects.equals(lastName, "0")) {
                s.setLastName(lastName);
            }

            try {
                StudentRepository.updateStudent(s);
                isSuccess = true;
                System.out.println("### Student  data updated ###\n");
                System.out.println();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!isSuccess);
    }

    private static void removeStudent() {
        Utils.printTitle("REMOVE A STUDENT");
        ArrayList<Student> list = StudentRepository.getStudents();
        if (list.size() == 0) {
            System.out.println("Student list is empty!\n");
            return;
        }
        System.out.println("┌────────────┬──────────────────────────────┬────────────────┐");
        System.out.println("│   ID       │       Student Name           │   Due amount   │");
        System.out.println("├────────────┼──────────────────────────────┼────────────────┤");
        for (Student student : list) {
            System.out.println("│ " + String.format("%-11s", student.getUserId()) + "│ " + String.format("%-29s", student.getFullName()) + "│ " + String.format("%8.2f/- BDT", student.getUnpaidAmount()) + " │");
        }
        System.out.println("└────────────┴──────────────────────────────┴────────────────┘");
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
            System.out.print("Enter NSU ID (0 to back) ");
            System.out.println("ID Must contain 10 Digits.");
            String userId = scanner.next();
            if (Objects.equals(userId, "0")) {
                System.out.println();
                break;
            }
            System.out.print("Enter First Name        : ");
            scanner = new Scanner(System.in);
            String firstName = scanner.nextLine();
            System.out.print("Enter Last Name         : ");
            scanner = new Scanner(System.in);
            String lastName = scanner.nextLine();
            System.out.print("Set a password          : ");
            scanner = new Scanner(System.in);
            String password = scanner.next();
            System.out.print("Re-enter the password   : ");
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
        if (list.size() == 0) {
            System.out.println("Student list is empty!\n");
            return;
        }
        System.out.println("┌────────────┬──────────────────────────────┬────────────────┐");
        System.out.println("│    ID      │        Student Name          │   Due amount   │");
        System.out.println("├────────────┼──────────────────────────────┼────────────────┤");
        for (Student s : list) {
            System.out.println("│ " + String.format("%-11s", s.getUserId()) +
                    "│ " + String.format("%-29s", s.getFullName()) +
                    "│ " + String.format("%8.2f/- BDT", s.getUnpaidAmount()) + " │");
        }
        System.out.println("└────────────┴──────────────────────────────┴────────────────┘");
        scanner = new Scanner(System.in);
        System.out.print("Press 'Enter' to go back ");
        scanner.nextLine();
        System.out.println();
    }

    private static void changeAdminPass() {
        Utils.printTitle("Change Password");
        boolean isSuccess = false;

        do {
            System.out.print("Enter your old Password (0 to cancel): ");
            String oldPass = scanner.next();
            if (Objects.equals(oldPass, "0")) {
                System.out.println();
                break;
            }
            System.out.print("Enter your new password: ");
            String newPass = scanner.next();
            System.out.print("Re-enter your new password: ");
            String rNewPass = scanner.next();
            if (!Objects.equals(oldPass, currentUser.getPassword()) || !Objects.equals(newPass, rNewPass)) {
                System.out.print("Password did not match!!\n");
                continue;
            }
            AdminRepository.updateAdminPassword(currentUser.getUserId(), newPass);
            isSuccess = true;
            System.out.println("### Password changed ###\n");
        } while (!isSuccess);
    }
}


