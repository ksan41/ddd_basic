package com.ddd.basic.application.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Base64;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UserPostDto {
    private String email;
    private String name;
    private String password;

    public void decodePassword() {
        this.password = new String(Base64.getDecoder().decode(this.password));
    }
}
