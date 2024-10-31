package com.ddd.basic.service;

import com.ddd.basic.domain.User;
import com.ddd.basic.domain.circle.Circle;

public interface ICircleFactory {
    Circle create(String circleName, User owner);
}
