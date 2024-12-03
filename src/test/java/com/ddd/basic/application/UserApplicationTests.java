package com.ddd.basic.application;

import com.ddd.basic.application.user.UserApplicationService;
import com.ddd.basic.application.user.UserPostDto;
import com.ddd.basic.application.user.UserPutDto;
import com.ddd.basic.domain.model.user.IUserRepository;
import com.ddd.basic.domain.model.user.User;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UserApplicationTests {

    @Autowired private UserApplicationService userApplicationService;
    @Autowired private IUserRepository userRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @Test
    @Transactional
    @Rollback()
    public void 사용자_생성시_이메일_중복확인_실패_테스트() {
        createTestUser();
        UserPostDto newUser = new UserPostDto("admin@gmail.com", "사용자", "123456");

        Assertions.assertThrows(IllegalIdentifierException.class,
                () -> userApplicationService.register(newUser));
    }

    @Test
    @Transactional
    @Rollback
    public void 사용자_수정_실패_테스트() {
        Long userId = createTestUser();
        UserPutDto updateUserInfo = new UserPutDto("사용자", "123456", "5432");

        Assertions.assertThrows(IllegalIdentifierException.class,
                () -> userApplicationService.update(userId, updateUserInfo));
    }

    @Test
    @Transactional
    @Rollback
    public void 사용자_수정_성공_테스트() {
        Long userId = createTestUser();
        UserPutDto updateUserInfo = new UserPutDto("사용자", "1234", "qqq1234");
        userApplicationService.update(userId, updateUserInfo);

        User loadUser =  userRepository.find(userId).orElse(new User());
        Assertions.assertEquals("사용자", loadUser.getName());
        Assertions.assertTrue(passwordEncoder.matches("qqq1234", loadUser.getPassword()));
    }

    private Long createTestUser() {
        UserPostDto user = new UserPostDto("admin@gmail.com","관리자", "1234");
        return userApplicationService.register(user);
    }
}
