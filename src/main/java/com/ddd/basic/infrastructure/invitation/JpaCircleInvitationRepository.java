package com.ddd.basic.infrastructure.invitation;

import com.ddd.basic.domain.model.invitation.CircleInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCircleInvitationRepository extends JpaRepository<CircleInvitation, Long> {
}
