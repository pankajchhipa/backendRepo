package com.dummy.project.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private String userCode;
    private String fName;
    private String lastName;
    private String email;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

}
