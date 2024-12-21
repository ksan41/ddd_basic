package com.ddd.basic.application.circle;

import com.ddd.basic.application.user.UserApplicationService;
import com.ddd.basic.application.user.UserPostDto;
import com.ddd.basic.domain.model.service.CircleService;
import com.ddd.basic.domain.model.user.IUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
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
        UserPostDto newUser = new UserPostDto(email, name, "YWFhMTIzNDUh"); // aaa12345!
        return userApplicationService.register(newUser);
    }

    @Test
    public void 서클_검색_테스트() {
        Long ownerUserId = createTestUser("aaa@gmail.com", "홍길동");

        for (int i = 0; i < 100; i++) {
            CircleCreateDto circle = new CircleCreateDto(ownerUserId, String.format("서클%d", i));
            circleApplicationService.create(circle);
        }
        log.debug("==========================================================");
        CircleSearchDto searchInfo = new CircleSearchDto("서클", 0, "name");

        List<CircleViewDto> foundList = circleApplicationService.search(searchInfo).getContent();
        int idx = 0;
        for (int i = 99; i>=90 ; i--) {
            Assertions.assertEquals(String.format("서클%d", i), foundList.get(idx++).getCircleName());
        }
    }
}