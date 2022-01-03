package src.com.nsu.rds.ui;

import src.com.nsu.rds.data.models.Courses;
import src.com.nsu.rds.data.models.User;
import src.com.nsu.rds.data.repositories.CourseRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class AdvisingUI {
    static Scanner scanner = new Scanner(System.in);
    static User currentUser;

    static void homeScreen(User user) {

        currentUser = user;
        System.out.println("Welcome " + user.getUserId());
        boolean isDone = false;

        do {
            System.out.println("\n1. See offered course list");
            System.out.println("2. Add a new course");
            System.out.println("3. Your course list");
            System.out.println("4. Remove a course");
            System.out.println("0. Back");

            System.out.print("Select an option: ");
            switch (scanner.nextInt()) {
                case 1 -> offeredCourseList();
                case 2 -> addCourse();
                case 3 -> myCourseList();
                case 4 -> removeCourse();
                case 0 -> isDone = true;
                default -> System.out.println("Wrong Input! Select Again: ");
            }
        } while (!isDone);
        LoginUI.showLoginScreen();
    }

    private static void myCourseList() {
        for (Courses c : CourseRepository.getCourses(currentUser.getUserId())) {
            System.out.println(c.getInitial() + "  " + c.getName() + "  " + c.getCredit());
        }
    }

    private static void removeCourse() {
    }

    private static void addCourse() {
        ArrayList<Courses> cList = CourseRepository.getCourses();

        for (int i = 0; i < cList.size(); i++) {
            System.out.println(i + 1 + " : " + cList.get(i).getInitial() + "  " + cList.get(i).getName() + "  " + cList.get(i).getCredit());
        }
        System.out.print("Enter Course no: ");
        int no = scanner.nextInt();
        CourseRepository.addCourse(currentUser.getUserId(), cList.get(no-1));
    }

    private static void offeredCourseList() {
        for (Courses c : CourseRepository.getCourses()) {
            System.out.println(c.getInitial() + "  " + c.getName() + "  " + c.getCredit());
        }
    }
}
