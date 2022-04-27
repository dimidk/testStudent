package app.model;

import org.springframework.context.annotation.Bean;

public class StudentCourses {

    private  int studentId;
    private String studName;
    private String coursename;

    public StudentCourses(){}

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return coursename;
    }

    public void setCourseName(String coursename) {
        this.coursename = coursename;
    }


}
