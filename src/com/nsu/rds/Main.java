package src.com.nsu.rds;

import src.com.nsu.rds.data.repositories.CourseRepository;
import src.com.nsu.rds.data.repositories.UserRepository;
import src.com.nsu.rds.models.Courses;
import src.com.nsu.rds.models.User;
import src.com.nsu.rds.ui.LoginUI;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        LoginUI.showLoginScreen();

        for (Courses c : CourseRepository.getCourses()) {
            System.out.println(c.getName() + " " + c.getCredit());
        }
        System.out.println();

        CourseRepository.addCourse(new Courses("BEN205", 3));

        for (Courses c : CourseRepository.getCourses()) {
            System.out.println(c.getName() + " " + c.getCredit());
        }
        System.out.println();
        CourseRepository.removeCourse("CSE215");

        for (Courses c : CourseRepository.getCourses()) {
            System.out.println(c.getName() + " " + c.getCredit());
        }

        UserRepository.setUsers(new ArrayList<>());

        for (User c : UserRepository.getUsers()) {
            System.out.println(c.getUsername() + " " + c.getPassword() + " " + c.isAdmin());
        }

    }
}
