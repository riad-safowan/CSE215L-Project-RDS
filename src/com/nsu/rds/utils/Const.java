package src.com.nsu.rds.utils;

public class Const {
    public static final String ALL_COURSE_LIST = "all-course-list.txt";
    public static final String ALL_USER_LIST = "all-user-list.txt";
    public static final String ALL_STUDENT_LIST = "all-student-list.txt";

    public static String getCourseFileName(String userId) {
        return userId + "-" + ALL_COURSE_LIST;
    }
}
