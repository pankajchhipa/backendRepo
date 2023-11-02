package com.dummy.project.base.dao;

import com.dummy.project.base.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    @Query("select u from UserEntity u where u.password = :password and (u.userCode = :userCode or u.email = :userCode)")
    List<UserEntity> checkConditionForQuery(@Param("userCode") String userCode, @Param("password") String password);

    Optional<UserEntity> findByEmail(String email);
    @Query("SELECT MAX(u.userCode) FROM UserEntity u")
    String findLatestUserCode();
}
