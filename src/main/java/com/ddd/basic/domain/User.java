package com.ddd.basic.domain;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String name;
    private String password;
}
