package app.controller;

import app.model.Student;
import app.model.StudentCourses;
import app.model.StudentGradeDAO;
import app.model.StudentGrades;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(path="/student")
public class StudentController {

    private  StudentGradeDAO student;

    public StudentController (StudentGradeDAO student){
        this.student = student;
    }

    //is Valid User and able to connect to database
    /*@PostMapping(path="/login/{username}/{password}")
    public Student isValidUser(@PathVariable String username,@PathVariable String password) {

        Student std = student.isValidUser(username,password);
        return std;
    }

    @GetMapping(path="/logout")
    public void logout() {
        //exit from Rest API

    }*/

    //getAllCourses for user
    @GetMapping(path="/{id}")
    public List<StudentCourses> getAllCourses(@PathVariable int id){

        return student.getStudCourses(id);
    }

    //getAllGrades for user
    @GetMapping(path="/grades/{id}")
    public List<StudentGrades> getAllCoursesGrades(@PathVariable int id) {

        return student.getStudGrades(id);
    }

    @GetMapping(path="/course/{course}")
    public HashMap<String,Float> getClassGradeCourse(@PathVariable String course) {

        HashMap<String,Float> res = new HashMap<>();
        res.put("Average",student.getAvgCourse(course));
        res.put("Maximum",student.getMaxCourse(course));
        res.put("Minimum",student.getMinCourse(course));

        return res;
    }

}
