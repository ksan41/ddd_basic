package com.ddd.basic.application.circle;

import lombok.Getter;

@Getter
public class CircleInviteDto {
    private Long fromUserId;
    private Long invitedUserId;
    private Long circleId;
}
