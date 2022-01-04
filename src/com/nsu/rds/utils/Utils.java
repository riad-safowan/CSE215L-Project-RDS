package src.com.nsu.rds.utils;

import src.com.nsu.rds.data.repositories.CourseRepository;
import src.com.nsu.rds.data.repositories.StudentRepository;
import src.com.nsu.rds.data.repositories.UserRepository;

public class Utils {
    public static void printTitle(String text) {
        StringBuilder line = new StringBuilder();
        line.append("=".repeat(Math.max(0, (60 - text.length()) / 2)));
        System.out.println("\n" + line + " " + text + " " + ((text.length() % 2 == 1) ? line + "=" : line));
    }

    public static void populateWithDummyData() {
        UserRepository.init();
        StudentRepository.init();
        CourseRepository.init();
        StudentRepository.setCourses(StudentRepository.getStudents().get(0).getUserId(), CourseRepository.getCourses());
    }
}
