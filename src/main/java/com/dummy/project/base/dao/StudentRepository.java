package com.dummy.project.base.dao;

import com.dummy.project.base.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, String> {
    Optional<StudentEntity> findByEmail(String email);

    @Query("SELECT MAX(s.id) FROM StudentEntity s")
    String findLatestId();
}
