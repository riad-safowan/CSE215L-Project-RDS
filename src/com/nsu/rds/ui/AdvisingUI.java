package src.com.nsu.rds.ui;

import src.com.nsu.rds.data.models.Courses;
import src.com.nsu.rds.data.models.Fee;
import src.com.nsu.rds.data.models.User;
import src.com.nsu.rds.data.repositories.AdminRepository;
import src.com.nsu.rds.data.repositories.CourseRepository;
import src.com.nsu.rds.data.repositories.StudentRepository;
import src.com.nsu.rds.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
            try {
                scanner = new Scanner(System.in);
                switch (scanner.nextInt()) {
                    case 1 -> offeredCourseList();
                    case 2 -> addCourse();
                    case 3 -> myCourseList();
                    case 4 -> removeCourse();
                    case 5 -> printAdvisingSlip();
                    case 0 -> isDone = true;
                    default -> System.out.println("Wrong Input! Select Again: \n");
                }
            } catch (Exception e) {
                System.out.println("Wrong Input! Select Again: \n");
            }
        } while (!isDone);
        StudentUI.homeScreen(user);
    }

    private static void printAdvisingSlip() {
        Fee fee = AdminRepository.getFees();
        Utils.printTitle("ADVISING SLIP");
        System.out.println();
        System.out.println("\t\t\t      North South University");
        System.out.println("\t\t     Student Registration - Fall 2021");
        System.out.println("\t\tDate : " + new SimpleDateFormat("EEEE, dd MMMM yyyy, hh:mm:ss a").format(Calendar.getInstance().getTime()));
        System.out.println();
        System.out.println("Student Name: " + String.format("%-30s", currentUser.getFullName()) + "ID# " + String.format("%-9s", currentUser.getUserId()) + "   Degree: Undergraduate");
        System.out.println();
        System.out.println("\t┌──────┬──────────┬──────────────┬──────────────────────┐");
        System.out.println("\t│  SL  │  Course  │    Credit    │        Tuition       │");
        System.out.println("\t├──────┼──────────┼──────────────┼──────────────────────┤");

        ArrayList<Courses> courses = StudentRepository.getCourses(currentUser.getUserId());
        double tuition = 0;
        for (int i = 0; i < courses.size(); i++) {
            System.out.println("\t│ " + String.format("%3d", i + 1) + "  │  " + String.format("%-7s", courses.get(i).getInitial()) + " │ " + String.format("%8.2f", courses.get(i).getCredit()) + "     │ " + String.format("%12.2f/- BDT", courses.get(i).getCredit() * fee.getCreditFee())+ "   │");
            tuition = tuition + (courses.get(i).getCredit() * fee.getCreditFee());
        }
        System.out.println("\t├──────┴──────────┴──────────────┴──────────────────────┤");
        System.out.println("\t│\t\t\t       Tuition Total :" + String.format("%13.2f/- BDT", tuition)+"   │");
        System.out.println("\t└───────────────────────────────────────────────────────┘");
        System.out.println();
        System.out.println("\t\t\t\tStudent Activity Fee        " + String.format("%7.2f/- BDT", fee.getActivityFee()));
        System.out.println("\t\t\t\tComputer Lab Fee            " + String.format("%7.2f/- BDT", fee.getComputerLabFee()));
        System.out.println("\t\t\t\tLibrary Fee                 " + String.format("%7.2f/- BDT", fee.getLibraryFee()));
        System.out.println("\t\t\t\tScience Lab Fee             " + String.format("%7.2f/- BDT", fee.getScienceLabFee()));
        double nonTuitionFee = fee.getActivityFee() + fee.getComputerLabFee() + fee.getLibraryFee() + fee.getScienceLabFee();
        System.out.println("\t\t\t\t------------------------------------------");
        System.out.println("\t\t\t\tTotal:                    " + String.format("%9.2f/- BDT", tuition + nonTuitionFee));
        System.out.println();
        System.out.println("\t\t\t\tLess: Non Tuition fees      " + String.format("%7.2f/- BDT", nonTuitionFee));
        double discount = tuition * fee.getWaiver() / 100;
        System.out.println("\t\t\t\tLess: Waiver " + fee.getWaiver() + "%         " + String.format("%7.2f/- BDT", discount));
        System.out.println("\t\t\t\t------------------------------------------");
        System.out.println("\t\t\t\tPayable:                  " + String.format("%9.2f/- BDT", (tuition - discount)));
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println();
    }

    private static void myCourseList() {
        Utils.printTitle("Your Courses");
        System.out.println("┌──────────┬───────────────────────────────┬────────────┐");
        System.out.println("│ Initial  │         Course Name           │   Credit   │");
        System.out.println("├──────────┼───────────────────────────────┼────────────┤");
        for (Courses c : StudentRepository.getCourses(currentUser.getUserId())) {
            System.out.println("│ " + String.format("%-9s", c.getInitial()) + "│ " + String.format("%-30s", c.getName())
                    + "│ " + String.format("%10.2f", c.getCredit()) + " │");
        }
        System.out.println("└──────────┴───────────────────────────────┴────────────┘");
    }

    private static void removeCourse() {
        ArrayList<Courses> cList = StudentRepository.getCourses(currentUser.getUserId());
        System.out.println("┌────┬──────────┬───────────────────────────────┬────────────┐");
        System.out.println("│ No │ Initial  │         Course Name           │   Credit   │");
        System.out.println("├────┼──────────┼───────────────────────────────┼────────────┤");
        for (int i = 0; i < cList.size(); i++) {
            System.out.println("│ " + String.format("%2d", i + 1) + " │ " + String.format("%-9s", cList.get(i).getInitial()) + "│ " + String.format("%-30s", cList.get(i).getName())
                    + "│ " + String.format("%10.2f", cList.get(i).getCredit()) + " │");
        }
        System.out.println("└────┴──────────┴───────────────────────────────┴────────────┘");
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
        Utils.printTitle("Add Course Menu");
        System.out.println("┌────┬──────────┬───────────────────────────────┬────────────┐");
        System.out.println("│ No │ Initial  │         Course Name           │   Credit   │");
        System.out.println("├────┼──────────┼───────────────────────────────┼────────────┤");
        for (int i = 0; i < cList.size(); i++) {
            System.out.println("│ " + String.format("%2d", i + 1) + " │ " + String.format("%-9s", cList.get(i).getInitial()) + "│ " + String.format("%-30s", cList.get(i).getName()) +
                    "│ " + String.format("%10.2f", cList.get(i).getCredit()) + " │");
        }
        System.out.println("└────┴──────────┴───────────────────────────────┴────────────┘");
        System.out.print("Enter Course no to add(0 to back): ");
        int no = scanner.nextInt();
        if (no == 0) System.out.println();
        else if (no > cList.size()) System.out.println("Invalid input!!\n");
        else {
            StudentRepository.addCourse(currentUser.getUserId(), cList.get(no - 1));
            System.out.println();
        }
    }

    private static void offeredCourseList() {
        Utils.printTitle("Offered Course List");
        System.out.println("┌──────────┬───────────────────────────────┬────────────┐");
        System.out.println("│ Initial  │         Course Name           │   Credit   │");
        System.out.println("├──────────┼───────────────────────────────┼────────────┤");
        for (Courses c : CourseRepository.getCourses()) {
            System.out.println("│ "+String.format("%-9s", c.getInitial()) + "│ " + String.format("%-30s", c.getName()) + "│ " + String.format("%10.2f", c.getCredit()) + " │");
        }
        System.out.println("└──────────┴───────────────────────────────┴────────────┘");

    }
}
