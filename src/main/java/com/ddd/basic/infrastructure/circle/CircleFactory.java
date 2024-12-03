package com.ddd.basic.infrastructure.circle;

import com.ddd.basic.domain.model.circle.Circle;
import com.ddd.basic.domain.model.circle.ICircleFactory;
import com.ddd.basic.domain.model.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CircleFactory implements ICircleFactory {
    @Override
    public Circle create(String circleName, User owner) {
        return new Circle(circleName, owner, new ArrayList<>());
    }
}
