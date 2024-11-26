package com.ddd.basic.domain.model.user;


import java.util.List;

public interface IUserRepository {
    User find(Long userId);
    User find(String email);
    List<User> findAll();
    Long save(User user);
}
