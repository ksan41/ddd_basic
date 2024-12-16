package com.ddd.basic.application.circle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CircleJoinDto {
    private Long userId;
    private Long circleId;

    public CircleJoinDto(Long userId, Long circleId) {
        this.userId = userId;
        this.circleId = circleId;
    }
}
