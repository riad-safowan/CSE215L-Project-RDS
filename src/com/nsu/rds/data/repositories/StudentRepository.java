package src.com.nsu.rds.data.repositories;

import src.com.nsu.rds.data.models.Student;
import src.com.nsu.rds.data.models.User;
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
    public static ArrayList<Student> students = new ArrayList<>();

    public static void init() {
        students.addAll(
                List.of(
                        new Student("s1", "1", false, "Developer", "Riad", "Safowan", List.of(), 21000),
                        new Student("s2", "2", false, "Developer", "Rubayet", "Adbir", List.of(), 24000),
                        new Student("s3", "3", false, "Developer", "Faisal", "Sakib", List.of(), 45000)
                )
        );
        setStudents(students);
    }

    public static void setStudents(ArrayList<Student> students) {
        ArrayList<User> users = new ArrayList(students);
        users.addAll(UserRepository.getUsers());
        UserRepository.setUsers(users);
        try {
            FileWriter myWriter = new FileWriter(Const.ALL_STUDENT_LIST);
            for (Student s : students) {
                myWriter.write(s.getUserId() + " " + s.getPassword() + " " + s.isAdmin() + " " + s.getAddedBy() + " " + s.getFullName() + " " + s.getUnpaidAmount() + "\n");
            }
            myWriter.close();
            for (Student s : students) createCourseFile(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(Student s) throws Exception {
        ArrayList<Student> students = getStudents();
        students.add(s);
        UserRepository.addUser(s);
        createCourseFile(s);
        setStudents(students);
    }

    private static void createCourseFile(Student s) {
        try {
            File myObj = new File(Const.getCourseFileName(s.getUserId()));
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred creating file.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> getStudents() {
        ArrayList<Student> newList = new ArrayList<>();
        try {
            File myObj = new File(Const.ALL_STUDENT_LIST);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                newList.add(new Student(myReader.next(), myReader.next(), !Objects.equals(myReader.next(), "false"), myReader.next(), myReader.next(), myReader.next(), List.of(), myReader.nextDouble()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newList;
    }

}
