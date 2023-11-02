package com.dummy.project.base.controller;

import com.dummy.project.base.dto.StudentDTO;
import com.dummy.project.base.entity.StudentEntity;
import com.dummy.project.base.service.Response;
import com.dummy.project.base.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/getStudent/{id}")
    public Response<Object> getUserInformationByUserCode(
            @PathVariable(name = "id", required = true) String id) {
        StudentEntity result = studentService.getStudentById(id);
        if (result.getId() == null) {
            return Response.builder()
                    .succeed(true)
                    .error(true)
                    .Result("Student Not Found").build();
        }
        return Response.builder()
                .succeed(true)
                .Result(result).build();
    }

    @GetMapping(value = "/getAll")
    public List<StudentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/save")
    public String signupUser(@RequestBody StudentDTO studentDTO) throws IOException {
        return studentService.saveStudent(studentDTO);
    }

    @PutMapping(value = "/update")
    public String signupUser(@Valid @RequestBody StudentDTO studentDTO,
                             @RequestParam(name = "studentId") String studentId) {
        return studentService.updateStudent(studentDTO, studentId);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteStudent(
            @PathVariable(name = "id") String id) {
        return studentService.deleteStudent(id);
    }
}
