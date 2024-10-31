package com.ddd.basic.service;

import com.ddd.basic.domain.circle.Circle;
import com.ddd.basic.domain.circle.CircleCreateDto;
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
        if (Objects.nonNull(foundCircle)) return true;
        return false;
    }

    private boolean isDuplicated(String circleName) {
        Circle foundCircle = circleRepository.find(circleName);
        if (Objects.nonNull(foundCircle)) return true;
        return false;
    }
}
