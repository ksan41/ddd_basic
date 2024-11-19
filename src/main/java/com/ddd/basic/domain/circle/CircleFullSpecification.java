package com.ddd.basic.domain.circle;

public class CircleFullSpecification {
    public boolean isSatisfiedBy(Circle circle) {
        long premiumUserNumber = circle.getMembers().stream()
                .mapToInt(user -> user.isPremium() ? 1 : 0)
                .count();
        long circleUppserLimit = premiumUserNumber < 10 ? 30 : 50;
        return circle.countMembers() >= circleUppserLimit;
    }
}
