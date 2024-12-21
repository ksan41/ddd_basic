package com.ddd.basic.domain.model.user;

import com.ddd.basic.application.user.UserPostDto;
import com.ddd.basic.common.constants.ExceptionMessage;
import com.ddd.basic.domain.shared.ISpecification;

import java.util.regex.Pattern;

public class UserRegistValidationSpecification implements ISpecification<UserPostDto> {

    @Override
    public boolean isSatisfied(UserPostDto userInfo) throws IllegalArgumentException{
        if (!Pattern.matches("^([가-힣]{2,12})$", userInfo.getName())) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_VALID_USER_NAME.getMessage());
        }

        if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$", userInfo.getPassword())) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_VALID_USER_PASSWORD.getMessage());
        }
        return true;
    }
}
