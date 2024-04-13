package com.dummy.project.base.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "student")
public class StudentEntity {

    @Id
    private String id;
    private String fName;
    private String lName;
    private String email;
    private String classType;
    private Integer age;
//    @Lob
//    private byte[] image;
//    private String imageType;
//    private String imageP;ath
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;


    @PrePersist
    protected void onCreate() {
        created = new Date();
        updated = new Date();
    }

}

