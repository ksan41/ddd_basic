package com.ddd.basic.application.invitation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InvitationResponseDto {
    private Long circleId;
    private Long invitationId;
    private boolean isAccept;
}
