package src.com.nsu.rds.ui;

import src.com.nsu.rds.data.models.Courses;
import src.com.nsu.rds.data.models.Fee;
import src.com.nsu.rds.data.models.User;
import src.com.nsu.rds.data.repositories.AdminRepository;
import src.com.nsu.rds.data.repositories.CourseRepository;
import src.com.nsu.rds.data.repositories.StudentRepository;
import src.com.nsu.rds.utils.Const;
import src.com.nsu.rds.utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
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
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n\t\t North South University\n");
        stringBuilder.append("\t\t Student Registration - Fall 2021\n");
        stringBuilder.append("\t\t Date : ").append(new SimpleDateFormat("EEEE, dd MMMM yyyy, hh:mm:ss a").format(Calendar.getInstance().getTime())).append("\n\n");
        stringBuilder.append("Student Name: ").append(String.format("%-30s", currentUser.getFullName())).append("ID# ").append(String.format("%-9s", currentUser.getUserId())).append("   Degree: Undergraduate\n\n");
        stringBuilder.append("\t┌──────┬──────────┬──────────────┬──────────────────────┐\n");
        stringBuilder.append("\t│  SL  │  Course  │    Credit    │        Tuition       │\n");
        stringBuilder.append("\t├──────┼──────────┼──────────────┼──────────────────────┤\n");

        ArrayList<Courses> courses = StudentRepository.getCourses(currentUser.getUserId());
        double tuition = 0;
        for (int i = 0; i < courses.size(); i++) {
            stringBuilder.append("\t│ ").append(String.format("%3d", i + 1)).append("  │  ").append(String.format("%-7s", courses.get(i).getInitial())).append(" │ ").append(String.format("%8.2f", courses.get(i).getCredit())).append("     │ ").append(String.format("%12.2f/- BDT", courses.get(i).getCredit() * fee.getCreditFee())).append("   │\n");
            tuition = tuition + (courses.get(i).getCredit() * fee.getCreditFee());
        }
        stringBuilder.append("\t├──────┴──────────┴──────────────┴──────────────────────┤\n");
        stringBuilder.append("\t│\t\t\t       Tuition Total :").append(String.format("%13.2f/- BDT", tuition)).append("   │\n");
        stringBuilder.append("\t└───────────────────────────────────────────────────────┘\n\n");
        stringBuilder.append("\t\t\t\tStudent Activity Fee        ").append(String.format("%7.2f/- BDT\n", fee.getActivityFee()));
        stringBuilder.append("\t\t\t\tComputer Lab Fee            ").append(String.format("%7.2f/- BDT\n", fee.getComputerLabFee()));
        stringBuilder.append("\t\t\t\tLibrary Fee                 ").append(String.format("%7.2f/- BDT\n", fee.getLibraryFee()));
        stringBuilder.append("\t\t\t\tScience Lab Fee             ").append(String.format("%7.2f/- BDT\n", fee.getScienceLabFee()));
        double nonTuitionFee = fee.getActivityFee() + fee.getComputerLabFee() + fee.getLibraryFee() + fee.getScienceLabFee();
        stringBuilder.append("\t\t\t\t------------------------------------------\n");
        stringBuilder.append("\t\t\t\tTotal:                    ").append(String.format("%9.2f/- BDT\n\n", tuition + nonTuitionFee));
        stringBuilder.append("\t\t\t\tLess: Non Tuition fees      ").append(String.format("%7.2f/- BDT\n", nonTuitionFee));
        double discount = tuition * fee.getWaiver() / 100;
        stringBuilder.append("\t\t\t\tLess: Waiver ").append(fee.getWaiver()).append("%         ").append(String.format("%7.2f/- BDT\n", discount));
        stringBuilder.append("\t\t\t\t------------------------------------------\n");
        stringBuilder.append("\t\t\t\tPayable:                  ").append(String.format("%9.2f/- BDT\n\n", (tuition - discount)));
        stringBuilder.append("------------------------------------------------------------\n\n");

        System.out.println(stringBuilder);

        scanner = new Scanner(System.in);
        System.out.print("Press 'p' to print slip (0 to back): ");
        String s = scanner.next();
        if (Objects.equals(s, "p") || Objects.equals(s, "P")) {
            try (FileWriter fileWriter = new FileWriter(Const.getAdvisingSlipName(currentUser.getUserId(), "fall-21"))) {
                fileWriter.write(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Advising slip printed as " + Const.getAdvisingSlipName(currentUser.getUserId(), "fall-21"));
            System.out.println();
        } else {
            System.out.println();
        }

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
            System.out.println("│ " + String.format("%-9s", c.getInitial()) + "│ " + String.format("%-30s", c.getName()) + "│ " + String.format("%10.2f", c.getCredit()) + " │");
        }
        System.out.println("└──────────┴───────────────────────────────┴────────────┘");

    }
}
