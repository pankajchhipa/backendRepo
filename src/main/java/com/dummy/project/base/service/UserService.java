package com.dummy.project.base.service;

import com.dummy.project.base.dao.UserRepository;
import com.dummy.project.base.dto.UserDTO;
import com.dummy.project.base.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public UserEntity getUserByUserCode(String usercode);
    public List<UserEntity> getAllUsers();
    public String signupUser(UserDTO user);
    public UserEntity loginUser(String username, String password);

}
