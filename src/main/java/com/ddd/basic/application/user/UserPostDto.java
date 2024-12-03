package com.ddd.basic.application.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserPostDto {
    private String email;
    private String name;
    private String password;

}
