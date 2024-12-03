package com.ddd.basic.application.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserPutDto {
    private String name;
    private String originPassword;
    private String changePassword;
}
