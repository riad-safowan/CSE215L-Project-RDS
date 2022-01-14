package src.com.nsu.rds.data.repositories;

import src.com.nsu.rds.data.models.Courses;
import src.com.nsu.rds.data.models.Student;
import src.com.nsu.rds.data.models.User;
import src.com.nsu.rds.utils.Const;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentRepository {
    public static ArrayList<Student> students = new ArrayList<>();

    public static void init() {
        students.addAll(
                List.of(
                        new Student("s", "1", false, "Developer", "Riad", "Safowan", List.of(), 21000)
                )
        );
        setStudents(students);
    }

    public static void setStudents(ArrayList<Student> students) {
        ArrayList<User> users = new ArrayList(students);
        users.addAll(AdminRepository.getAdmins());
        AdminRepository.setUsers(users);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Const.ALL_STUDENT_LIST))) {
            for (Student s : students) {
                outputStream.writeObject(s);
            }
            for (Student s : students) createCourseFile(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(Student s) throws Exception {
        ArrayList<Student> students = getStudents();
        students.add(s);
        AdminRepository.addUser(s); //throws exception if user already exist
        createCourseFile(s);
        setStudents(students);
    }

    public static Student getStudentById(String userId) {
        ArrayList<Student> list = getStudents();
        for (Student s : list) {
            if (Objects.equals(s.getUserId(), userId)) return s;
        }
        return new Student();
    }

    private static void createCourseFile(Student s) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Const.getCourseFileName(s.getUserId())))) {

        } catch (IOException e) {
            System.out.println("An error occurred creating file.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> getStudents() {
        ArrayList<Student> newList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Const.ALL_STUDENT_LIST))) {
            while (true) {
                newList.add((Student) inputStream.readObject());
            }
        } catch (EOFException e) {
            return newList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newList;
    }

    public static void setCourses(String userId, ArrayList<Courses> courses) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Const.getCourseFileName(userId)))) {
            for (Courses c : courses) {
                outputStream.writeObject(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Courses> getCourses(String userId) {
        ArrayList<Courses> newList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Const.getCourseFileName(userId)))) {
            while (true) {
                newList.add((Courses) inputStream.readObject());
            }
        } catch (EOFException e) {
            return newList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newList;
    }

    public static void addCourse(String userId, Courses c) {
        ArrayList<Courses> list = getCourses(userId);
        list.add(c);
        setCourses(userId, list);
    }

    public static void removeCourse(String userId, String c) {
        ArrayList<Courses> list = getCourses(userId);
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
        }
        setCourses(userId, list);
    }

    public static boolean removeStudent(String id) {
        ArrayList<Student> list = getStudents();
        int index = 0;
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i).getUserId(), id)) {
                index = i;
                found = true;
            }
        }
        if (found) {
            list.remove(index);
            AdminRepository.removeUser(id);
            removeStudentsCourse(id);
        }
        setStudents(list);
        return found;
    }

    private static void removeStudentsCourse(String id) {
        File myObj = new File(Const.getCourseFileName(id));
        myObj.delete();
    }

    public static void updatePassword(String userId, String newP) {
        ArrayList<Student> students = getStudents();
        for (Student s : students) {
            if (Objects.equals(s.getUserId(), userId)) s.setPassword(newP);
        }
        setStudents(students);
    }
}
