package com.ddd.basic.infrastructure.circle;

import com.ddd.basic.domain.model.circle.Circle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCircleRepository extends JpaRepository<Circle, Long> {

    Optional<Circle> findByName(String name);
}
