package src.com.nsu.rds.data.repositories;

import src.com.nsu.rds.models.Courses;
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
    private ArrayList<Courses> courses = new ArrayList<>();

    void init() {
        courses.addAll(List.of(
                new Courses("CSE115", 3),
                new Courses("CSE115L", 1),
                new Courses("CSE215", 3),
                new Courses("CSE215L", 1),
                new Courses("MAT116", 3),
                new Courses("MAT120", 3),
                new Courses("MAT130", 3)));
    }

    public ArrayList<Courses> getCourses() {
        ArrayList<Courses> newList = new ArrayList<>();
        try {
            File myObj = new File(Const.ALL_COURSE_LIST);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                newList.add(new Courses(myReader.next(), myReader.nextInt()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newList;
    }

    public void setCourses(ArrayList<Courses> courses) {
//        init();
        try {
            FileWriter myWriter = new FileWriter(Const.ALL_COURSE_LIST);
            for (Courses c : courses) {
                myWriter.write(c.getName() + " " + c.getCredit() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCourse(Courses c) {
        ArrayList<Courses> list = getCourses();
        list.add(c);
        setCourses(list);
    }

    public void removeCourse(String c) {
        ArrayList<Courses> list = getCourses();
        int index = 0;
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i).getName(), c)) {
                index = i;
                found = true;
            }
        }
        if (found) {
            list.remove(index);
        }
        setCourses(list);
    }
}