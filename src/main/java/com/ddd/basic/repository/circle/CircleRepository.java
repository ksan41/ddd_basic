package com.ddd.basic.repository.circle;

import com.ddd.basic.domain.circle.Circle;

public interface CircleRepository {
    void save(Circle circle);
    Circle find(Long circleId);
    Circle find(String circleName);
}
