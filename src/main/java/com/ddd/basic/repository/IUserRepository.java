package com.ddd.basic.repository;


import com.ddd.basic.domain.user.User;

public interface IUserRepository {
    User find(Long userId);
}
