package com.ddd.basic.domain.model.circle;


import com.ddd.basic.domain.model.user.User;

public interface ICircleFactory {
    Circle create(String circleName, User owner);
}
