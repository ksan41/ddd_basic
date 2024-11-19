package com.ddd.basic.repository.circle;

import lombok.Getter;

@Getter
public class CircleInviteDto {
    private Long fromUserId;
    private Long invitedUserId;
    private Long circleId;
}
