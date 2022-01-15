package com.nsu.rds.utils;

public class Const {
    public static final String ALL_COURSE_LIST = "all-course-list.dat";
    public static final String ALL_USER_LIST = "all-user-list.dat";
    public static final String ALL_STUDENT_LIST = "all-student-list.dat";
    public static final String ALL_FEES_LIST = "all-fees-list.dat";

    public static String getCourseFileName(String userId) {
        return userId + "-" + ALL_COURSE_LIST;
    }

    public static String getAdvisingSlipName(String userId, String semester) {
        return userId + "-" + semester + "-" + ALL_COURSE_LIST;
    }
}
