package app.model;

public class StudentGrades {

    private  int studentId;
    private String studName;
    private String coursename;
    private float grade;

    public StudentGrades(){}

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourseName() {
        return coursename;
    }

    public void setCourseName(String coursename) {
        this.coursename = coursename;
    }

    public float getGrade() {
        return grade;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
