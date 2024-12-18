package com.ddd.basic.domain.model.service;

import com.ddd.basic.common.constants.ExceptionMessage;
import com.ddd.basic.domain.model.user.User;
import com.ddd.basic.domain.model.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public boolean isDuplicated(String email) {
        return userRepository.find(email).isPresent();
    }

    public boolean isPasswordMatched(Long userId, String inputPassword) throws NullPointerException {
        User user = userRepository.find(userId).orElseThrow(() -> new NullPointerException(ExceptionMessage.NOT_FOUND_USER.getMessage()));
        return passwordEncoder.matches(inputPassword, user.getPassword());
    }
}
