package com.ddd.basic.domain.model.circle;

import com.ddd.basic.domain.shared.ISpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICircleRepository {
    Circle save(Circle circle);
    Optional<Circle> find(Long circleId);
    Optional<Circle> find(String circleName);

    List<Circle> findAll();
    Page<Circle> search(String keyword, Pageable pageInfo);
    List<Circle> findRecommended(ISpecification<Circle> recommendSpec);
}
