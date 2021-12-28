package src.com.nsu.rds;

import src.com.nsu.rds.data.Data;
import src.com.nsu.rds.models.Courses;
import src.com.nsu.rds.models.User;
import src.com.nsu.rds.ui.LoginUI;
import src.com.nsu.rds.ui.UI;

import java.util.ArrayList;

public class Main {
    static Data data = new Data();

    public static void main(String[] args) {
        LoginUI.showLoginScreen();

//        for (Courses c : data.getCourses()) {
//            System.out.println(c.getName() + " " + c.getCredit());
//        }
//        System.out.println();
//
//        data.addCourse(new Courses("BEN205", 3));
//
//        for (Courses c : data.getCourses()) {
//            System.out.println(c.getName() + " " + c.getCredit());
//        }
//        System.out.println();
//        data.removeCourse("CSE215");
//
//        for (Courses c : data.getCourses()) {
//            System.out.println(c.getName() + " " + c.getCredit());
//        }

//        data.setUsers(new ArrayList<>());
//
//        for (User c : data.getUsers()) {
//            System.out.println(c.getName() + " " + c.getPassword() + " " + c.isAdmin());
//        }

    }
}
