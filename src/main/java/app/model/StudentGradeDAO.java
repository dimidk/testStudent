package app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class StudentGradeDAO {

    private final JdbcTemplate jdbc;

    @Autowired
    public StudentGradeDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }


  public List<StudentCourses> getStudCourses(String username) {

        String sql = "select student.id,student.fullname,courses.coursename from courses " +
                        "inner join studentCourses on studentCourses.courseid = courses.id" +
                        " inner join student on studentCourses.studid = student.id and student.username = ?";

        List<StudentCourses> stdCourses = jdbc.query(sql,new PreparedStatementSetter(){
           public void setValues(PreparedStatement preparedStatement) throws SQLException {
               preparedStatement.setString(1,username);
           }},new studCoursesMapper());

        return stdCourses;
    }


    public List<StudentGrades> getStudGrades(String name) {

        String sql = "select student.id,student.fullname,courses.coursename, studentGrades.grade from courses " +
                "inner join studentGrades on studentGrades.courseid = courses.id" +
                " inner join student on studentGrades.studid = student.id and student.username = ?";

        List<StudentGrades> stdGrades = jdbc.query(sql,new PreparedStatementSetter(){
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,name);
            }},new studGradesMapper());

        return stdGrades;
    }

    public float getAvgCourse(String course) {

        float avgGrade = jdbc.queryForObject("select avg(grade) from studentGrades inner join courses on" +
                " studentGrades.courseid = courses.id and courses.coursename = ?",Float.class,course);
        return avgGrade;
    }

    public float getMaxCourse(String course) {

        float maxGrade = jdbc.queryForObject("select max(grade) from studentGrades inner join courses on" +
                " studentGrades.courseid = courses.id and courses.coursename = ?",Float.class,course);
        return maxGrade;

    }

    public float getMinCourse(String course) {

        float minGrade = jdbc.queryForObject("select min(grade) from studentGrades inner join courses on" +
                " studentGrades.courseid = courses.id and courses.coursename = ?",Float.class,course);
        return minGrade;

    }

    public float getMedianCourse(String course) throws SQLException {

        float medianGrade;
        float max = getMaxCourse(course);
        float min = getMinCourse(course);
        medianGrade = (max + min) / 2;
        return medianGrade;

    }

    private static final class studMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int index) throws SQLException {
            Student td = new Student();
            td.setSemester(rs.getInt("semester"));
            td.setCardID(rs.getString("cardID"));
            td.setFullName(rs.getString("fullName"));
            td.setId(rs.getInt("id"));
            return td;
        }
    }

    private static final class studCoursesMapper implements RowMapper<StudentCourses> {

        @Override
        public StudentCourses mapRow(ResultSet rs, int index) throws SQLException {
            StudentCourses td = new StudentCourses();
            td.setCourseName(rs.getString("coursename"));
            td.setStudName(rs.getString("fullname"));
            td.setStudentId(rs.getInt("id"));
            return td;
        }
    }

    private static final class studGradesMapper implements RowMapper<StudentGrades> {

        @Override
        public StudentGrades mapRow(ResultSet rs, int index) throws SQLException {
            StudentGrades td = new StudentGrades();
            td.setGrade(rs.getFloat("grade"));
            td.setStudentId(rs.getInt("id"));
            td.setCourseName(rs.getString("coursename"));
            td.setStudName(rs.getString("fullname"));

            return td;
        }
    }

}
