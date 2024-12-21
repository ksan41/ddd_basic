package com.ddd.basic.application.circle;

import com.ddd.basic.domain.model.circle.Circle;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CircleViewDto {

    private Long id;
    private String circleName;
    private String ownerName;
    private int memberCount;

    public CircleViewDto(Circle circle) {
        this.id = circle.getId();
        this.circleName = circle.getName();
        this.ownerName = circle.getOwner().getName();
        this.memberCount = circle.countMembers();
    }
}
