package com.dummy.project.base.service;

import com.dummy.project.base.dto.StudentDTO;
import com.dummy.project.base.entity.StudentEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    List<StudentEntity> getAllStudents();

    StudentEntity getStudentById(String id);

    String saveStudent(StudentDTO studentDTO);

    public String updateStudent(StudentDTO studentDTO, String id);

    public String deleteStudent(String id);

    public void saveProfilePhoto(MultipartFile image) throws IOException;
}
