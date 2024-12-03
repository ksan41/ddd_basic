package com.ddd.basic.infrastructure.circle;

import com.ddd.basic.domain.model.circle.Circle;
import com.ddd.basic.domain.model.circle.ICircleRepository;
import com.ddd.basic.domain.shared.ISpecification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CircleRepository implements ICircleRepository {
    @Override
    public void save(Circle circle) {

    }

    @Override
    public Circle find(Long circleId) {
        return null;
    }

    @Override
    public Circle find(String circleName) {
        return null;
    }

    @Override
    public List<Circle> findAll() {
        return null;
    }

    @Override
    public List<Circle> findRecommended(ISpecification<Circle> recommendSpec) {
        return null;
    }
}
