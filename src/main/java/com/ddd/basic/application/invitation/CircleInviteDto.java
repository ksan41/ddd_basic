package com.ddd.basic.application.invitation;

import lombok.Getter;

@Getter
public class CircleInviteDto {
    private Long fromUserId;
    private Long invitedUserId;
    private Long circleId;
}
