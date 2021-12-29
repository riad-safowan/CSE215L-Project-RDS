package src.com.nsu.rds.models;

import java.util.List;

public class Student extends User{
    private String firstName;
    private String lastName;
    private List<Courses> coursesList;
    private double unpaidAmount;

    public Student(String username, String password, boolean isAdmin, String firstName, String lastName, List<Courses> coursesList, double unpaidAmount) {
        super(username, password, isAdmin);
        this.firstName = firstName;
        this.lastName = lastName;
        this.coursesList = coursesList;
        this.unpaidAmount = unpaidAmount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName+ " "+ lastName;
    }

    public List<Courses> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Courses> coursesList) {
        this.coursesList = coursesList;
    }

    public double getUnpaidAmount() {
        return unpaidAmount;
    }

    public void setUnpaidAmount(double unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }
}
