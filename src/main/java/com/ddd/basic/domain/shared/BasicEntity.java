package com.ddd.basic.domain.shared;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BasicEntity {
    private LocalDateTime createDate;

    private LocalDateTime deleteDate;

    protected void setCreateDateNow() {
        this.createDate = LocalDateTime.now();
    }

    protected void setDeleteDateNow() {
        this.deleteDate = LocalDateTime.now();
    }
}
