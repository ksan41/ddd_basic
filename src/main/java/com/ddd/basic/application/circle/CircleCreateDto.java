package com.ddd.basic.application.circle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CircleCreateDto {
    private Long ownerUserId;
    private String circleName;
}
