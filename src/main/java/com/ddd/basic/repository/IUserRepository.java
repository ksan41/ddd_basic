package com.ddd.basic.repository;


import com.ddd.basic.domain.User;

public interface IUserRepository {
    User find(String email);
}
