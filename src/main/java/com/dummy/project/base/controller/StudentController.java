package com.dummy.project.base.controller;

import com.dummy.project.base.dto.StudentDTO;
import com.dummy.project.base.entity.StudentEntity;
import com.dummy.project.base.service.Response;
import com.dummy.project.base.service.StudentService;
import jakarta.annotation.Resource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("*")
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

    @PutMapping(value = "/saveProfile")
    public String uploadProfilePhoto(
            @RequestParam(name = "image") MultipartFile image,
            @RequestParam(name = "studentCode") String studentCode){
        try {
            studentService.saveProfilePhoto(image, studentCode);
            return "Successfully uploaded";

        }
        catch (Exception e){
            System.out.println("Cought Exception : Stacetrack : " + Arrays.toString(e.getStackTrace()));
            return ("Cought Exception : Stacetrack : " + Arrays.toString(e.getStackTrace()));
        }
    }

    @GetMapping(value = "/getProfile")
    public ResponseEntity<byte[]> getProfilePhoto(
            @RequestParam(name = "code") String studentCode
    ) throws IOException {

        byte[] data = studentService.getProfilePhoto(studentCode);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // veryyyyyyyyyyy important Change MediaType as per your image type
                .body(data);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteStudent(
            @PathVariable(name = "id") String id) {
        return studentService.deleteStudent(id);
    }
}
