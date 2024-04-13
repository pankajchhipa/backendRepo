package com.dummy.project.base.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentProfile {

    @Id
    private String id;
    private String filePath;
    private String fileName;
//    @Lob
//    private byte[] fileData;

}
