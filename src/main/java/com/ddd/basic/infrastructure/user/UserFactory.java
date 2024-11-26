package com.ddd.basic.infrastructure.user;

import com.ddd.basic.domain.model.user.IUserFactory;
import com.ddd.basic.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class UserFactory implements IUserFactory {

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User create(String email, String name, String password) {
        String encryptedPw = encryptPassword(password);
        return new User(email, name, encryptedPw);
    }

    private String encryptPassword(String originPassword) {
        return passwordEncoder.encode(originPassword);
    }
}
