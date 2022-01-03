package src.com.nsu.rds.data.models;

import java.util.List;

public class Student extends User {
    private List<Courses> coursesList;
    private double unpaidAmount;

    public Student(String username, String password, boolean isAdmin, String addedBy, String firstName, String lastName, List<Courses> coursesList, double unpaidAmount) {
        super(username, password, isAdmin, addedBy, firstName, lastName);
        this.coursesList = coursesList;
        this.unpaidAmount = unpaidAmount;
    }

    public Student() {
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
