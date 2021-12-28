package src.com.nsu.rds;

import src.com.nsu.rds.data.Data;
import src.com.nsu.rds.models.Courses;
import src.com.nsu.rds.ui.UI;

public class Main {
    static Data data = new Data();

    public static void main(String[] args) {
        UI.showLoginScreen();

        for (Courses c : data.getCourses()) {
            System.out.println(c.getName() + " " + c.getCredit());
        }
        System.out.println();

        data.addCourse(new Courses("BEN205", 3));

        for (Courses c : data.getCourses()) {
            System.out.println(c.getName() + " " + c.getCredit());
        }
        System.out.println();
        data.removeCourse("CSE215");

        for (Courses c : data.getCourses()) {
            System.out.println(c.getName() + " " + c.getCredit());
        }
    }
}
