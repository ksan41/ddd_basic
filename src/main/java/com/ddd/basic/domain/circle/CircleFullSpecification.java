package com.ddd.basic.domain.circle;

import com.ddd.basic.common.ISpecification;

public class CircleFullSpecification implements ISpecification<Circle> {
    public boolean isSatisfied(Circle circle) {
        long premiumUserNumber = circle.getMembers().stream()
                .mapToInt(user -> user.isPremium() ? 1 : 0)
                .count();
        long circleUppserLimit = premiumUserNumber < 10 ? 30 : 50;
        return circle.countMembers() >= circleUppserLimit;
    }
}
