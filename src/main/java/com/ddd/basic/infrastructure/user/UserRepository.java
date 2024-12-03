package com.ddd.basic.infrastructure.user;

import com.ddd.basic.domain.model.user.IUserRepository;
import com.ddd.basic.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository implements IUserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public Optional<User> find(Long userId) {
        return jpaUserRepository.findById(userId);
    }

    @Override
    public Optional<User> find(String email) {
        return Optional.of(jpaUserRepository.findByEmail(email));
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll();
    }

    @Override
    public Long save(User user) {
        return jpaUserRepository.save(user).getId();
    }
}
