package com.ddd.basic.service;

import com.ddd.basic.domain.circle.Circle;
import com.ddd.basic.repository.circle.ICircleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CircleService {

    private final ICircleRepository circleRepository;

    public boolean exists(Circle circle) {
        Circle foundCircle = circleRepository.find(circle.getName());
        return Objects.nonNull(foundCircle);
    }

    private boolean isDuplicated(String circleName) {
        Circle foundCircle = circleRepository.find(circleName);
        return Objects.nonNull(foundCircle);
    }
}
