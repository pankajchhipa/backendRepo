package com.dummy.project.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {
    @NotEmpty
    private String fName;
    @NotEmpty
    private String lName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;


}
