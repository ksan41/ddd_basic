package com.ddd.basic.application.invitation;

import com.ddd.basic.application.circle.CircleInviteDto;
import com.ddd.basic.common.constants.ExceptionMessage;
import com.ddd.basic.domain.model.circle.Circle;
import com.ddd.basic.domain.model.circle.ICircleRepository;
import com.ddd.basic.domain.model.circle.spec.CircleFullSpecification;
import com.ddd.basic.domain.model.invitation.CircleInvitation;
import com.ddd.basic.domain.model.invitation.ICircleInvitationRepository;
import com.ddd.basic.domain.model.user.IUserRepository;
import com.ddd.basic.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CircleInvitationApplicationService {

    private final ICircleInvitationRepository circleInvitationRepository;
    private final IUserRepository userRepository;
    private final ICircleRepository circleRepository;
    private final CircleFullSpecification circleFullSpec;

    public void invite(CircleInviteDto inviteInfo) throws NullPointerException, NegativeArraySizeException{
        User fromUser = userRepository.find(inviteInfo.getFromUserId()).orElseThrow(()->new NullPointerException(ExceptionMessage.NOT_FOUND_USER.getMessage()));
        User invitedUser = userRepository.find(inviteInfo.getInvitedUserId()).orElseThrow(()->new NullPointerException(ExceptionMessage.NOT_FOUND_USER.getMessage()));

        Circle circle = circleRepository.find(inviteInfo.getCircleId()).orElseThrow(() -> new NullPointerException(ExceptionMessage.NOT_FOUND_CIRCLE.getMessage()));

        if (circleFullSpec.isSatisfied(circle)) {
            throw new NegativeArraySizeException(ExceptionMessage.FULL_CIRCLE_MEMBERS.getMessage());
        }
        CircleInvitation circleInvitation = new CircleInvitation(circle, fromUser, invitedUser);
        circleInvitationRepository.save(circleInvitation);
    }
}
