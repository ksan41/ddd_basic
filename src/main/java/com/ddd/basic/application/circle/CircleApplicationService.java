package com.ddd.basic.application.circle;

import com.ddd.basic.common.constants.ExceptionMessage;
import com.ddd.basic.domain.model.circle.*;
import com.ddd.basic.domain.model.circle.spec.CircleFullSpecification;
import com.ddd.basic.domain.model.circle.spec.CircleRecommendSpecification;
import com.ddd.basic.domain.model.circleuser.CircleUser;
import com.ddd.basic.domain.model.circleuser.ICircleUserRepository;
import com.ddd.basic.domain.model.service.CircleService;
import com.ddd.basic.domain.model.user.IUserRepository;
import com.ddd.basic.domain.model.user.User;
import com.ddd.basic.domain.model.invitation.CircleInvitation;
import com.ddd.basic.domain.model.invitation.ICircleInvitationRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CircleApplicationService {

    private final IUserRepository userRepository;
    private final ICircleFactory circleFactory;
    private final CircleService circleService;
    private final ICircleRepository circleRepository;
    private final ICircleUserRepository circleUserRepository;
    private final ICircleInvitationRepository circleInvitationRepository;
    private final CircleFullSpecification circleFullSpec;

    @Transactional
    public Long create(CircleCreateDto createCircle) throws IllegalArgumentException, NullPointerException {
        User owner = userRepository.find(createCircle.getOwnerUserId()).orElseThrow(() -> new NullPointerException(ExceptionMessage.NOT_FOUND_USER.getMessage()));
        Circle newCircle = circleFactory.create(createCircle.getCircleName(), owner);
        if (circleService.exists(newCircle)) {
            throw new IllegalIdentifierException(ExceptionMessage.DUPLICATED_CIRCLE_NAME.getMessage());
        }
        CircleUser circleUser = new CircleUser(owner, newCircle);
        circleUserRepository.save(circleUser);
        newCircle.getMembers().add(circleUser);
        return circleRepository.save(newCircle).getId();
    }

    @Transactional
    public void join(CircleJoinDto joinInfo) throws NegativeArraySizeException, NullPointerException{
        User joinMember = userRepository.find(joinInfo.getUserId()).orElseThrow(() -> new NullPointerException(ExceptionMessage.NOT_FOUND_USER.getMessage()));

        Circle circle = circleRepository.find(joinInfo.getCircleId()).orElseThrow(() -> new NullPointerException(ExceptionMessage.NOT_FOUND_CIRCLE.getMessage()));
        if (!circleFullSpec.isSatisfied(circle)) {
            throw new NegativeArraySizeException(ExceptionMessage.FULL_CIRCLE_MEMBERS.getMessage());
        }
        CircleUser circleUser = new CircleUser(joinMember, circle);
        circleUserRepository.save(circleUser);
        circle.join(circleUser);
    }

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

    public List<Circle> getRecommend() {
        CircleRecommendSpecification circleRecommendSpec = new CircleRecommendSpecification(LocalDateTime.now());

        return circleRepository.findRecommended(circleRecommendSpec)
                .stream().limit(10).toList();
    }
}
