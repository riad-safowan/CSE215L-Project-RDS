package src.com.nsu.rds.data.repositories;

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

public class StudentRepository {
    private static ArrayList<Student> students = new ArrayList<>();

    static void init() {
        students.addAll(
                List.of(
                        new Student("student1", "12345", false, "FirstName", "LastName", List.of(), 0),
                        new Student("student2", "12345", false, "FirstName", "LastName", List.of(), 0),
                        new Student("student3", "12345", false, "FirstName", "LastName", List.of(), 0)
                )
        );
    }

    public static void setStudents(ArrayList<Student> students) {
        init();
        try {
            FileWriter myWriter = new FileWriter(Const.ALL_STUDENT_LIST);
            for (Student s : students) {
                myWriter.write(s.getUsername() + " " + s.getPassword() + " " + s.getFullName() + " " + s.getUnpaidAmount() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(Student s) {
        ArrayList<Student> students = getStudents();
        students.add(s);
        UserRepository.addUser(s);
        setStudents(students);
    }

    private static ArrayList<Student> getStudents() {
        ArrayList<Student> newList = new ArrayList<>();
        try {
            File myObj = new File(Const.ALL_STUDENT_LIST);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                newList.add(new Student(myReader.next(), myReader.next(), !Objects.equals(myReader.next(), "false"), myReader.next(), myReader.next(), List.of(), myReader.nextDouble()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newList;
    }

}
