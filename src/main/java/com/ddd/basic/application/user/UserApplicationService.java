package com.ddd.basic.application.user;

import com.ddd.basic.common.constants.ExceptionMessage;
import com.ddd.basic.domain.model.service.UserService;
import com.ddd.basic.domain.model.user.IUserFactory;
import com.ddd.basic.domain.model.user.User;
import com.ddd.basic.domain.model.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

@RequiredArgsConstructor
@Service
public class UserApplicationService {

    private final IUserRepository userRepository;
    private final UserService userService;
    private final IUserFactory userFactory;
    private final BCryptPasswordEncoder passwordEncoder;

    public Long register(UserPostDto postUser) throws IllegalIdentifierException{
        if (userService.isDuplicated(postUser.getEmail())) {
            throw new IllegalIdentifierException(ExceptionMessage.DUPLICATED_USER_EMAIL.getMessage());
        }
        User user = userFactory.create(postUser.getEmail(), postUser.getName(), postUser.getPassword());
        return userRepository.save(user);
    }

    public String login(UserLoginDto loginInfo) throws NullPointerException {
        String token = "";
        User user = userRepository.find(loginInfo.getEmail()).orElseThrow(() -> new NullPointerException(ExceptionMessage.NOT_FOUND_USER.getMessage()));
        String inputPassword = new String(Base64.getDecoder().decode(loginInfo.getPassword()));
        if (userService.isPasswordMatched(user.getId(), inputPassword)) {
            // 토큰 관련 처리..
        } else {
            throw new IllegalIdentifierException(ExceptionMessage.AUTHENTICATION_FAILED.getMessage());
        }
        return token;
    }

    @Transactional
    public void update(Long userId, UserPutDto putUserInfo) throws NullPointerException, IllegalArgumentException, IllegalIdentifierException{
        User user = userRepository.find(userId).orElseThrow(() -> new NullPointerException(ExceptionMessage.NOT_FOUND_USER.getMessage()));
        if (userService.isPasswordMatched(userId, putUserInfo.getOriginPassword())) {
            user.changeName(putUserInfo.getName());
            user.changePassword(putUserInfo.getChangePassword(), passwordEncoder);
        } else {
            throw new IllegalIdentifierException(ExceptionMessage.AUTHENTICATION_FAILED.getMessage());
        }
    }

    @Transactional
    public void withdrawal(Long userId) throws NullPointerException{
        User user = userRepository.find(userId).orElseThrow(() -> new NullPointerException(ExceptionMessage.NOT_FOUND_USER.getMessage()));
        user.withdrawal();
    }
}