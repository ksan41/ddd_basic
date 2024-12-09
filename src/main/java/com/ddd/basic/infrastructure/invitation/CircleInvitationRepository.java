package com.ddd.basic.infrastructure.invitation;

import com.ddd.basic.domain.model.invitation.CircleInvitation;
import com.ddd.basic.domain.model.invitation.ICircleInvitationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CircleInvitationRepository implements ICircleInvitationRepository {

    private final JpaCircleInvitationRepository jpaCircleInvitationRepository;
    @Override
    public CircleInvitation save(CircleInvitation invitation) {
        return jpaCircleInvitationRepository.save(invitation);
    }
}
