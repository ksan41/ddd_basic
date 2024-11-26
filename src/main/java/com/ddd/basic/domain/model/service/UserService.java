package com.ddd.basic.domain.model.service;

import com.ddd.basic.domain.model.user.User;
import com.ddd.basic.domain.model.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService {

    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public boolean isDuplicated(String email) {
        User foundUser = userRepository.find(email);
        return Objects.nonNull(foundUser);
    }

    public boolean isPasswordMatched(Long userId, String inputPassword) {
        User user = userRepository.find(userId);
        return passwordEncoder.matches(inputPassword, user.getPassword());
    }
}
