package com.ddd.basic.domain.model.invitation;


import java.util.Optional;

public interface ICircleInvitationRepository {

    CircleInvitation save(CircleInvitation invitation);
    Optional<CircleInvitation> find(Long invitationId);
}
