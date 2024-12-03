package com.ddd.basic.domain.model.user;


import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    Optional<User> find(Long userId);
    Optional<User> find(String email);
    List<User> findAll();
    Long save(User user);
}
