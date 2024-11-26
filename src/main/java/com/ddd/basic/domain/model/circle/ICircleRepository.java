package com.ddd.basic.domain.model.circle;

import com.ddd.basic.domain.shared.ISpecification;

import java.util.List;

public interface ICircleRepository {
    void save(Circle circle);
    Circle find(Long circleId);
    Circle find(String circleName);

    List<Circle> findAll();
    List<Circle> findRecommended(ISpecification<Circle> recommendSpec);
}
