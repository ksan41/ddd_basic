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
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CircleApplicationService {
    private final int PAGE_SIZE = 10;
    private final IUserRepository userRepository;
    private final ICircleFactory circleFactory;
    private final CircleService circleService;
    private final ICircleRepository circleRepository;
    private final ICircleUserRepository circleUserRepository;
    private final CircleFullSpecification circleFullSpec;

    public Page<CircleViewDto> search(CircleSearchDto searchInfo) {
        String searchKeyword = "";
        if (Objects.nonNull(searchInfo.getKeyword())) {
            searchKeyword = searchInfo.getKeyword();
        }
        Pageable page = PageRequest.of(searchInfo.getPage(), PAGE_SIZE, Sort.by(Sort.Direction.DESC, searchInfo.getSortBy()));

        return circleRepository.search(searchKeyword, page).map(CircleViewDto::new);
    }

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

    public List<Circle> getRecommend() {
        CircleRecommendSpecification circleRecommendSpec = new CircleRecommendSpecification(LocalDateTime.now());

        return circleRepository.findRecommended(circleRecommendSpec)
                .stream().limit(10).toList();
    }
}
