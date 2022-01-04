package src.com.nsu.rds.ui;

import src.com.nsu.rds.data.models.Courses;
import src.com.nsu.rds.data.models.User;
import src.com.nsu.rds.data.repositories.CourseRepository;
import src.com.nsu.rds.data.repositories.StudentRepository;
import src.com.nsu.rds.utils.Utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class AdvisingUI {
    static Scanner scanner = new Scanner(System.in);
    static User currentUser;

    static void homeScreen(User user) {

        currentUser = user;
        Utils.printTitle("ADVISING WINDOW");
        boolean isDone = false;

        do {
            System.out.println("1 -> See offered course list");
            System.out.println("2 -> Add a new course");
            System.out.println("3 -> Your course list");
            System.out.println("4 -> Remove a course");
            System.out.println("5 -> Print advising slip");
            System.out.println("0 -> Back");

            System.out.print("Select an option: ");
            switch (scanner.nextInt()) {
                case 1 -> offeredCourseList();
                case 2 -> addCourse();
                case 3 -> myCourseList();
                case 4 -> removeCourse();
                case 5 -> printAdvisingSlip();
                case 0 -> isDone = true;
                default -> System.out.println("Wrong Input! Select Again: ");
            }
        } while (!isDone);
        StudentUI.homeScreen(user);
    }

    private static void printAdvisingSlip() {
        Utils.printTitle("ADVISING SLIP");
        System.out.println();
        System.out.println("\t\tNorth South University");
        System.out.println("\t\tStudent Registration - Fall 2021");
        System.out.println("\t\tDate : " + new SimpleDateFormat("EEEE, dd MMMM yyyy, hh:mm:ss a").format(Calendar.getInstance().getTime()));
        System.out.println();
        System.out.println("Student Name: " + String.format("%-20s", currentUser.getFullName()) + "ID# " + String.format("%-7s", currentUser.getUserId()) + "      Degree: Undergraduate");
        System.out.println();
        System.out.println("\t SL    Course       Credit       Tuition");

        ArrayList<Courses> courses = StudentRepository.getCourses(currentUser.getUserId());
        double total = 0;
        for (int i = 0; i < courses.size(); i++) {
            System.out.println("\t" + String.format("%2d", i + 1) + "     " + String.format("%-7s", courses.get(i).getInitial()) + "        " + courses.get(i).getCredit() + "          " + String.format("%-7.2f", courses.get(i).getCredit() * 6500.0));
            total = total + courses.get(i).getCredit() * 65000.0;
        }
        System.out.println("\t\t\t\t\tTuition Total: " + String.format("%9.2f", total));

        System.out.println();
    }

    private static void myCourseList() {
        for (Courses c : StudentRepository.getCourses(currentUser.getUserId())) {
            System.out.println(String.format("%-7s", c.getInitial()) + "  " + String.format("%-30s", c.getName()) + "  " + c.getCredit());
        }
        System.out.println();
    }

    private static void removeCourse() {
        ArrayList<Courses> cList = StudentRepository.getCourses(currentUser.getUserId());
        for (int i = 0; i < cList.size(); i++) {
            System.out.println(i + 1 + " : " + String.format("%-7s", cList.get(i).getInitial()) + "  " + String.format("%-30s", cList.get(i).getName()) + "  " + cList.get(i).getCredit());
        }
        System.out.print("Enter Course no to remove(0 to back): ");
        int no = scanner.nextInt();
        if (no == 0) System.out.println();
        else if (no > cList.size()) System.out.println("Invalid input!!\n");
        else {
            StudentRepository.removeCourse(currentUser.getUserId(), cList.get(no - 1).getInitial());
            System.out.println();
        }
    }

    private static void addCourse() {
        ArrayList<Courses> cList = CourseRepository.getCourses();

        for (int i = 0; i < cList.size(); i++) {
            System.out.println(i + 1 + " : " + String.format("%-7s", cList.get(i).getInitial()) + "  " + String.format("%-30s", cList.get(i).getName()) + "  " + cList.get(i).getCredit());
        }
        System.out.print("Enter Course no to remove(0 to back): ");
        int no = scanner.nextInt();
        if (no == 0) System.out.println();
        else if (no > cList.size()) System.out.println("Invalid input!!\n");
        else {
            StudentRepository.addCourse(currentUser.getUserId(), cList.get(no - 1));
            System.out.println();
        }
    }

    private static void offeredCourseList() {
        System.out.println();
        for (Courses c : CourseRepository.getCourses()) {
            System.out.println(String.format("%-7s", c.getInitial()) + "  " + String.format("%-30s", c.getName()) + "  " + c.getCredit());
        }
        System.out.println();
    }
}
