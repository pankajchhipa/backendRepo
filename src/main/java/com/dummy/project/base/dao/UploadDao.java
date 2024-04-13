package com.dummy.project.base.dao;

import com.dummy.project.base.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UploadDao extends JpaRepository<StudentProfile , String>{

}
