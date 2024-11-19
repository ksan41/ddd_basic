package com.ddd.basic.domain.circle;

import lombok.Getter;

@Getter
public class CircleInviteDto {
    private Long fromUserId;
    private Long invitedUserId;
    private Long circleId;
}
