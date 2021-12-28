package src.com.nsu.rds.data;

import src.com.nsu.rds.data.repositories.CourseRepository;
import src.com.nsu.rds.data.repositories.UserRepository;
import src.com.nsu.rds.models.Courses;
import src.com.nsu.rds.models.User;

import java.util.ArrayList;

public class Data {
    private CourseRepository courseRepository = new CourseRepository();
    private UserRepository userRepository = new UserRepository();

    public ArrayList<Courses> getCourses() {
        return courseRepository.getCourses();
    }

    public void addCourse(Courses c) {
        courseRepository.addCourse(c);
    }

    public void removeCourse(String s) {
        courseRepository.removeCourse(s);
    }

    public ArrayList<User> getUsers() {
        return userRepository.getUsers();
    }

    public void setUsers(ArrayList<User> users) {
        userRepository.setUsers(users);
    }
}
