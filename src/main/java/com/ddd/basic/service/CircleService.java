package com.ddd.basic.service;

import com.ddd.basic.domain.circle.Circle;
import com.ddd.basic.repository.circle.CircleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class CircleService {

    private final CircleRepository circleRepository;

    public boolean exists(Circle circle) {
        if (isDuplicated(circle.getName())) {
            // 중복오류
        }
        return true;
    }

    private boolean isDuplicated(String circleName) {
        Circle foundCircle = circleRepository.find(circleName);
        if (Objects.nonNull(foundCircle)) return true;
        return false;
    }
}
