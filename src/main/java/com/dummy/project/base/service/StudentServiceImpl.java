package com.dummy.project.base.service;

import com.dummy.project.base.dao.StudentRepository;
import com.dummy.project.base.dto.StudentDTO;
import com.dummy.project.base.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    public static final String filePath = "C:/Users/91855/Downloads/Dummy/image/";
    @Autowired
    private StudentRepository repository;

    @Override
    public List<StudentEntity> getAllStudents() {

        return repository.findAll();
    }

    @Override
    public StudentEntity getStudentById(String id) {
        Optional<StudentEntity> studentEntity = repository.findById(id);
        if (studentEntity.isPresent()) {
            return studentEntity.get();
        }
        return new StudentEntity();
    }

    public void saveProfilePhoto(@RequestParam(name = "image") MultipartFile image) throws IOException {
        String fullPath = filePath + image.getOriginalFilename();

        image.transferTo(new File(fullPath));
    }

    @Override
    public String saveStudent(StudentDTO studentDTO) {

        Optional<StudentEntity> byEmail = repository.findByEmail(studentDTO.getEmail());
        if (byEmail.isPresent()) {
            return "{\"output\":\"Student already  exisits\"}";
        }

        StudentEntity entity =
                StudentEntity.builder()
                        .fName(studentDTO.getFname())
                        .lName(studentDTO.getLname())
                        .age(studentDTO.getAge())
                        .email(studentDTO.getEmail())
                        .classType(studentDTO.getClassType())
                        .id(generateNextId()).build();

        StudentEntity save = repository.save(entity);
        return "{\"output\":\"Student Created Successfully\"}";
    }

    @Override
    public String updateStudent(StudentDTO studentDTO, String id) {

        Optional<StudentEntity> studentEntity = repository.findById(id);


        StudentEntity entity =
                StudentEntity.builder()
                        .fName(studentDTO.getFname())
                        .lName(studentDTO.getLname())
                        .age(studentDTO.getAge())
                        .email(studentDTO.getEmail())
                        .classType(studentDTO.getClassType())
                        .updated(new Date())
                        .created(studentEntity.get().getCreated())
                        .id(id).build();

        StudentEntity save = repository.save(entity);
        return "{\"output\":\"Student updated Successfully\"}";
    }

    @Override
    public String deleteStudent(String id) {
        this.repository.deleteById(id);
        return "{\"output\":\"Student " + id + " deleted Successfully\"}";
    }

    public String generateNextId() {
        String id = repository.findLatestId();
        int nextNumber = 1;

        if (id != null) {
            try {
                nextNumber = Integer.parseInt(id.substring(1)) + 1;
            } catch (NumberFormatException e) {
                // Handle the case where the user code format is incorrect.
            }
        }

        return "S" + String.format("%03d", nextNumber);
    }


}
