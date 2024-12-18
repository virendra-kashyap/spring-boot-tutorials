package com.virendra.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL_ID")
    private String emailId;
}
