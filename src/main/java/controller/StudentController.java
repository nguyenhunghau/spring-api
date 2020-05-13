package controller;

import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.StudentService;

import java.util.List;

/**
 * Created by CoT on 7/29/18.
 */
@RestController
@RequestMapping(path = "/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(path = "student", method = RequestMethod.GET)
    public List<Student> getStudentsByName(@RequestParam String s){
        return studentService.findStudents(s);
    }
}
