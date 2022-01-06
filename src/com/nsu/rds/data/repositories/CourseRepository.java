package src.com.nsu.rds.data.repositories;

import src.com.nsu.rds.data.models.Courses;
import src.com.nsu.rds.data.models.Student;
import src.com.nsu.rds.utils.Const;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CourseRepository {
    public static ArrayList<Courses> courses = new ArrayList<>();

    public static void init() {
        courses.addAll(List.of(
                new Courses("CSE115", "Programing_language_I", 3),
                new Courses("CSE115L", "Programing_language_I_Lab", 1),
                new Courses("CSE215", "Programing_language_II", 3),
                new Courses("CSE215L", "Programing_language_II_Lab", 1),
                new Courses("MAT116", "Precalculus", 3),
                new Courses("MAT120", "Calculus_I", 3),
                new Courses("MAT130", "Calculus_II", 3),
                new Courses("MAT250", "Calculus_III", 3),
                new Courses("PHY107", "Physics_I", 3),
                new Courses("PHY108", "Physics_II", 3)
        ));
        setCourses(courses);
    }

    public static ArrayList<Courses> getCourses() {
        ArrayList<Courses> newList = new ArrayList<>();
        try {
            File myObj = new File(Const.ALL_COURSE_LIST);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                newList.add(new Courses(myReader.next(), myReader.next(), myReader.nextDouble()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newList;
    }

    public static void setCourses(ArrayList<Courses> courses) {
        try {
            FileWriter myWriter = new FileWriter(Const.ALL_COURSE_LIST);
            for (Courses c : courses) {
                myWriter.write(c.getInitial() + " " + c.getName() + " " + c.getCredit() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addCourse(Courses c) throws Exception {
        ArrayList<Courses> list = getCourses();
        for (Courses courses : list) {
            if (Objects.equals(courses.getInitial(), c.getInitial()))
                throw new Exception("Course already exits! ");
        }
        list.add(c);
        setCourses(list);
    }

    public static void removeCourse(String c) {
        ArrayList<Courses> list = getCourses();
        int index = 0;
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i).getInitial(), c)) {
                index = i;
                found = true;
            }
        }
        if (found) {
            list.remove(index);
            for (Student student : StudentRepository.getStudents()) {
                StudentRepository.removeCourse(student.getUserId(), c);
            }
        }
        setCourses(list);
    }
}

