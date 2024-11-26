package com.ddd.basic.domain.model.circle.spec;

import com.ddd.basic.domain.model.circle.Circle;
import com.ddd.basic.domain.shared.ISpecification;

import java.time.LocalDateTime;

public class CircleRecommendSpecification implements ISpecification<Circle> {
    private LocalDateTime executeTime;

    public CircleRecommendSpecification(LocalDateTime executeTime) {
        this.executeTime = executeTime;
    }

    public boolean isSatisfied(Circle circle) {
        if (circle.countMembers() < 10) {
            return false;
        }
        return executeTime.minusMonths(1).isBefore(circle.getCreateDate());
    }
}
