package com.ddd.basic.application.user;

import com.ddd.basic.domain.model.user.User;
import com.ddd.basic.domain.model.user.UserGrade;

public class UserViewDto {
    private String email;
    private String name;
    private UserGrade grade;

    public UserViewDto(User user) throws NullPointerException{
        this.email = user.getEmail();
        this.name = user.getName();
        this.grade = user.getGrade();
    }
}
