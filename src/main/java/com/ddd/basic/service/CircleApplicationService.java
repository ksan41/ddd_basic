package com.ddd.basic.service;

import com.ddd.basic.domain.User;
import com.ddd.basic.domain.circle.Circle;
import com.ddd.basic.domain.circle.CircleCreateDto;
import com.ddd.basic.domain.circle.CircleInviteDto;
import com.ddd.basic.domain.circle.CircleJoinDto;
import com.ddd.basic.domain.invvitation.CircleInvitation;
import com.ddd.basic.repository.ICircleInvitationRepository;
import com.ddd.basic.repository.IUserRepository;
import com.ddd.basic.repository.circle.ICircleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CircleApplicationService {

    private final IUserRepository userRepository;
    private final ICircleFactory circleFactory;
    private final CircleService circleService;
    private final ICircleRepository circleRepository;
    private final ICircleInvitationRepository circleInvitationRepository;

    @Transactional
    public void create(CircleCreateDto circle) {
        User owner = userRepository.find(circle.getOwnerUserEmail());
        if (!Objects.nonNull(owner)) {
            // not found
        }
        Circle newCircle = circleFactory.create(circle.getCircleName(), owner);
        if (circleService.exists(newCircle)) {
            // 중복오류
        }
        circleRepository.save(newCircle);
    }

    public void join(CircleJoinDto joinInfo) {
        User joinMember = userRepository.find(joinInfo.getUserId());
        if (!Objects.nonNull(joinMember)) {
            // not found
        }
        Circle circle = circleRepository.find(joinInfo.getCircleId());
        if (!Objects.nonNull(circle)) {
            // not found
        }
        if (circle.getMembers().size() >= 29) {
            // circle full
        }
        circle.getMembers().add(joinMember);
        circleRepository.save(circle);
    }

    public void invite(CircleInviteDto inviteInfo) {
        User fromUser = userRepository.find(inviteInfo.getFromUserEmail());
        if (!Objects.nonNull(fromUser)) {
            // not found
        }

        User invitedUser = userRepository.find(inviteInfo.getInvitedUserEmail());
        if (!Objects.nonNull(invitedUser)) {
            // not fount
        }

        Circle circle = circleRepository.find(inviteInfo.getCircleId());
        if (!Objects.nonNull(circle)) {
            // not found
        }
        if (circle.getMembers().size() >= 29) {
            // circle full
        }
        CircleInvitation circleInvitation = new CircleInvitation(circle, fromUser, invitedUser);
        circleInvitationRepository.save(circleInvitation);
    }
}
