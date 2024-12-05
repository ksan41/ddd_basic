package com.ddd.basic.domain.model.service;

import com.ddd.basic.domain.model.circle.Circle;
import com.ddd.basic.domain.model.circle.ICircleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CircleService {

    private final ICircleRepository circleRepository;

    public boolean exists(Circle circle) {
        return circleRepository.find(circle.getName()).isPresent();
    }
}
