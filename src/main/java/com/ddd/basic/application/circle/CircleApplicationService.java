package com.ddd.basic.application.circle;

import com.ddd.basic.common.constants.ExceptionMessage;
import com.ddd.basic.domain.model.circle.*;
import com.ddd.basic.domain.model.circle.spec.CircleFullSpecification;
import com.ddd.basic.domain.model.circle.spec.CircleRecommendSpecification;
import com.ddd.basic.domain.model.service.CircleService;
import com.ddd.basic.domain.model.user.User;
import com.ddd.basic.domain.invvitation.CircleInvitation;
import com.ddd.basic.domain.ICircleInvitationRepository;
import com.ddd.basic.domain.model.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CircleApplicationService {

    private final IUserRepository userRepository;
    private final ICircleFactory circleFactory;
    private final CircleService circleService;
    private final ICircleRepository circleRepository;
    private final ICircleInvitationRepository circleInvitationRepository;
    private CircleFullSpecification circleFullSpec = new CircleFullSpecification();

    @Transactional
    public void create(CircleCreateDto createCircle) throws IllegalArgumentException, NullPointerException {
        User owner = userRepository.find(createCircle.getOwnerUserId());
        if (!Objects.nonNull(owner)) {
            throw new NullPointerException(ExceptionMessage.NOT_FOUND_USER.getMessage());
        }
        Circle newCircle = circleFactory.create(createCircle.getCircleName(), owner);
        if (circleService.exists(newCircle)) {
            throw new IllegalIdentifierException(ExceptionMessage.DUPLICATED_CIRCLE_NAME.getMessage());
        }
        circleRepository.save(newCircle);
    }

    public void join(CircleJoinDto joinInfo) throws IllegalArgumentException, NullPointerException{
        User joinMember = userRepository.find(joinInfo.getUserId());
        if (!Objects.nonNull(joinMember)) {
            throw new NullPointerException(ExceptionMessage.NOT_FOUND_USER.getMessage());
        }
        Circle circle = circleRepository.find(joinInfo.getCircleId());
        if (!Objects.nonNull(circle)) {
            throw new NullPointerException(ExceptionMessage.NOT_FOUND_CIRCLE.getMessage());
        }

        if (circleFullSpec.isSatisfied(circle)) {
            throw new IllegalArgumentException(ExceptionMessage.FULL_CIRCLE_MEMBERS.getMessage());
        }
        circle.join(joinMember);
        circleRepository.save(circle);
    }

    public void invite(CircleInviteDto inviteInfo) {
        User fromUser = userRepository.find(inviteInfo.getFromUserId());
        if (!Objects.nonNull(fromUser)) {
            throw new NullPointerException(ExceptionMessage.NOT_FOUND_USER.getMessage());
        }

        User invitedUser = userRepository.find(inviteInfo.getInvitedUserId());
        if (!Objects.nonNull(invitedUser)) {
            throw new NullPointerException(ExceptionMessage.NOT_FOUND_USER.getMessage());
        }

        Circle circle = circleRepository.find(inviteInfo.getCircleId());
        if (!Objects.nonNull(circle)) {
            throw new NullPointerException(ExceptionMessage.NOT_FOUND_CIRCLE.getMessage());
        }
        if (circleFullSpec.isSatisfied(circle)) {
            throw new NegativeArraySizeException(ExceptionMessage.FULL_CIRCLE_MEMBERS.getMessage());
        }
        CircleInvitation circleInvitation = new CircleInvitation(circle, fromUser, invitedUser);
        circleInvitationRepository.save(circleInvitation);
    }

    public List<Circle> getRecommend(CircleGetRecommendDto recommendRequest) {
        CircleRecommendSpecification circleRecommendSpec = new CircleRecommendSpecification(LocalDateTime.now());

        return circleRepository.findRecommended(circleRecommendSpec)
                .stream().limit(10).toList();
    }
}
