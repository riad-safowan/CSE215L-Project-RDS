package src.com.nsu.rds.data;

import src.com.nsu.rds.models.Courses;

import java.util.ArrayList;

public class Data {
    private Repository repository= new Repository();

    public ArrayList<Courses> getCourses(){
       return repository.getCourses();
    }

    public void addCourse(Courses c) {
        repository.addCourse(c);
    }

    public void removeCourse(String s) {
        repository.removeCourse(s);
    }
}
