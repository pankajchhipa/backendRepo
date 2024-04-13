package com.dummy.project.base.controller;

import com.dummy.project.base.dto.UserDTO;
import com.dummy.project.base.entity.UserEntity;
import com.dummy.project.base.service.Response;
import com.dummy.project.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getUser/{userCode}")
    public Response<Object> getUserInformationByUserCode(
            @PathVariable(name = "userCode", required = true) String userCode) throws InterruptedException {

//        Thread.sleep(10000);
        UserEntity result = userService.getUserByUserCode(userCode);
        if (result.getUserCode() == null) {
            return Response.builder()
                    .succeed(true)
                    .Result("User Not Found").build();
        }
        return Response.builder()
                .succeed(true)
                .Result(result).build();
    }

    @GetMapping(value = "/getUsers")
    public List<UserEntity> getUsersInformations() throws InterruptedException {
        Thread.sleep(3000);
        return userService.getAllUsers();
    }

    @PostMapping(value = "/signup")
    public String signupUser(@Valid @RequestBody UserDTO user) {
        return userService.signupUser(user);
    }

    @GetMapping(value = "/login")
    public UserEntity loginUser(@RequestParam String userCode, @RequestParam String userPassword) {
        UserEntity userEntity = userService.loginUser(userCode, userPassword);
        return userEntity;
    }

    @DeleteMapping(value = "/delete")
    public String deleteUserCode(@RequestParam String id){
        return userService.deleteUserCode(id);
    }
}
