package com.ddd.basic.repository.circle;

import com.ddd.basic.common.ISpecification;
import com.ddd.basic.domain.circle.Circle;

import java.util.List;

public interface ICircleRepository {
    void save(Circle circle);
    Circle find(Long circleId);
    Circle find(String circleName);

    List<Circle> findAll();
    List<Circle> findRecommended(ISpecification<Circle> recommendSpec);
}
