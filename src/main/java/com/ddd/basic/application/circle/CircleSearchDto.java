package com.ddd.basic.application.circle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CircleSearchDto {

    private String keyword;
    private int page;
    private String sortBy;
}
