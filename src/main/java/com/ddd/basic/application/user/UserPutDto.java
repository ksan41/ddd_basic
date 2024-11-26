package com.ddd.basic.application.user;

import lombok.Getter;

@Getter
public class UserPutDto {
    private String name;
    private String originPassword;
    private String changePassword;
}
