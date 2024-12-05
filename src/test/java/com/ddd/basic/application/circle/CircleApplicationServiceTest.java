package com.ddd.basic.application.circle;

import com.ddd.basic.application.user.UserApplicationService;
import com.ddd.basic.application.user.UserPostDto;
import com.ddd.basic.common.constants.ExceptionMessage;
import com.ddd.basic.domain.model.circle.Circle;
import com.ddd.basic.domain.model.service.CircleService;
import com.ddd.basic.domain.model.user.IUserFactory;
import com.ddd.basic.domain.model.user.User;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class CircleApplicationServiceTest {

    @Autowired private UserApplicationService userApplicationService;
    @Autowired private CircleApplicationService circleApplicationService;
    @Autowired private CircleService circleService;
    @Autowired private IUserFactory userFactory;

    @Test
    public void 서클생성_서클명_중복_테스트() {
        Long ownerUserId = createTestUser("aaa@gmail.com", "홍길동");
        CircleCreateDto oldCircle = new CircleCreateDto(ownerUserId, "새서클");
        circleApplicationService.create(oldCircle);
        CircleCreateDto newCircle = new CircleCreateDto(ownerUserId, "새서클");

        Assertions.assertThrows(IllegalIdentifierException.class,
                () -> circleApplicationService.create(newCircle));
    }

    @Test
    public void 가입_회원수_제한_테스트() {
        Long ownerUserId = createTestUser("aaa@gmail.com", "홍길동");
        CircleCreateDto circle = new CircleCreateDto(ownerUserId, "새서클");
        Long circleId = circleApplicationService.create(circle);

        for(int i = 1; i < 30; i++) {
            Long userId = createTestUser(String.format("test%d@gamil.com", i), "동명이인");
            CircleJoinDto joinInfo = new CircleJoinDto(userId, circleId);
            circleApplicationService.join(joinInfo);
        }

        Long overUserId = createTestUser("user30@gamil.com", "초과인원");
        CircleJoinDto joinInfo = new CircleJoinDto(overUserId, circleId);

        Assertions.assertThrows(NegativeArraySizeException.class,
                () -> circleApplicationService.join(joinInfo));
    }

    private Long createTestUser(String email, String name) {
        UserPostDto newUser = new UserPostDto(email, name, "aaa12345");
        return userApplicationService.register(newUser);
    }
}