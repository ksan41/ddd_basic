package com.ddd.basic.domain.shared;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BasicEntity {
    private LocalDateTime createDate;
}
