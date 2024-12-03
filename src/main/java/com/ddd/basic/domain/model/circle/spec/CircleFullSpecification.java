package com.ddd.basic.domain.model.circle.spec;

import com.ddd.basic.domain.model.circle.Circle;
import com.ddd.basic.domain.shared.ISpecification;

public class CircleFullSpecification implements ISpecification<Circle> {
    public boolean isSatisfied(Circle circle) {
        long premiumUserNumber = circle.getMembers().stream()
                .mapToInt(user -> user.getUser().isPremium() ? 1 : 0)
                .count();
        long circleUppserLimit = premiumUserNumber < 10 ? 30 : 50;
        return circle.countMembers() >= circleUppserLimit;
    }
}
