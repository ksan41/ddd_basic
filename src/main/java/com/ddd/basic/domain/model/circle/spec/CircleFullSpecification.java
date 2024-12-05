package com.ddd.basic.domain.model.circle.spec;

import com.ddd.basic.domain.model.circle.Circle;
import com.ddd.basic.domain.shared.ISpecification;

public class CircleFullSpecification implements ISpecification<Circle> {
    public boolean isSatisfied(Circle circle) {
        long premiumUserNumber = circle.getMembers().stream()
                .filter(user -> user.getUser().isPremium())
                .count();
        long circleUppserLimit = premiumUserNumber < 10 ? 30 : 50;
        return circle.countMembers() <= circleUppserLimit;
    }
}
