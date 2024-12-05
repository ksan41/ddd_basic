package com.ddd.basic.infrastructure.circle;

import com.ddd.basic.domain.model.circle.Circle;
import com.ddd.basic.domain.model.circle.ICircleRepository;
import com.ddd.basic.domain.shared.ISpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class CircleRepository implements ICircleRepository {

    private final JpaCircleRepository jpaCircleRepository;

    @Override
    public void save(Circle circle) {
        jpaCircleRepository.save(circle);
    }

    @Override
    public Optional<Circle> find(Long circleId) {
        return jpaCircleRepository.findById(circleId);
    }

    @Override
    public Optional<Circle> find(String circleName) {
        return jpaCircleRepository.findByName(circleName);
    }

    @Override
    public List<Circle> findAll() {
        return jpaCircleRepository.findAll();
    }

    @Override
    public List<Circle> findRecommended(ISpecification<Circle> recommendSpec) {
        List<Circle> foundList = jpaCircleRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
        return foundList.stream().filter(recommendSpec::isSatisfied)
                        .collect(Collectors.toList());
    }
}
