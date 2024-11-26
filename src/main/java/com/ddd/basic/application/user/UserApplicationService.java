package com.ddd.basic.application.user;

import com.ddd.basic.common.constants.ExceptionMessage;
import com.ddd.basic.domain.model.service.UserService;
import com.ddd.basic.domain.model.user.IUserFactory;
import com.ddd.basic.domain.model.user.User;
import com.ddd.basic.domain.model.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserApplicationService {

    private final IUserRepository userRepository;
    private final UserService userService;
    private final IUserFactory userFactory;

    public Long register(UserPostDto postUser) throws IllegalIdentifierException{
        if (userService.isDuplicated(postUser.getEmail())) {
            throw new IllegalIdentifierException(ExceptionMessage.DUPLICATED_USER_EMAIL.getMessage());
        }
        User user = userFactory.create(postUser.getEmail(), postUser.getName(), postUser.getPassword());
        return userRepository.save(user);
    }

    public void update() {

    }

    public void delete() {

    }
}