package com.ddd.basic.domain.circle;

import lombok.Getter;

@Getter
public class CircleInviteDto {
    private String fromUserEmail;
    private String invitedUserEmail;
    private Long circleId;
}
