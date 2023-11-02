package com.dummy.project.base.service;

import com.dummy.project.base.dao.UserRepository;
import com.dummy.project.base.dto.UserDTO;
import com.dummy.project.base.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Tran
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity getUserByUserCode(String userCode) {
        Optional<UserEntity> userEntity = userRepository.findById(userCode);
        if (userEntity.isPresent()) {
            return userEntity.get();
        }
        return new UserEntity();
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String signupUser(UserDTO user) {

        Optional<UserEntity> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            return "User Already Exists";
        }
        UserEntity userEntity = UserEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .userCode(generateNextUserCode())
                .fName(user.getFName())
                .lastName(user.getLName())
                .build();
        UserEntity save = userRepository.save(userEntity);

        return "{\"output\":\"User Created Successfully\"}";
    }


    @Override
    public UserEntity loginUser(String username, String password) {

        List<UserEntity> userEntities = userRepository.checkConditionForQuery(username, password);
        if (userEntities.size() > 0) {
            return userEntities.get(0);
        }
        return new UserEntity();
    }

    public String generateNextUserCode() {
        String latestUserCode = userRepository.findLatestUserCode();
        int nextNumber = 1;

        if (latestUserCode != null) {
            try {
                nextNumber = Integer.parseInt(latestUserCode.substring(1)) + 1;
            } catch (NumberFormatException e) {
                // Handle the case where the user code format is incorrect.
            }
        }

        return "U" + String.format("%03d", nextNumber);
    }
}
