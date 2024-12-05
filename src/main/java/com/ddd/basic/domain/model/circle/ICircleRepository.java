package com.ddd.basic.domain.model.circle;

import com.ddd.basic.domain.shared.ISpecification;

import java.util.List;
import java.util.Optional;

public interface ICircleRepository {
    void save(Circle circle);
    Optional<Circle> find(Long circleId);
    Optional<Circle> find(String circleName);

    List<Circle> findAll();
    List<Circle> findRecommended(ISpecification<Circle> recommendSpec);
}
