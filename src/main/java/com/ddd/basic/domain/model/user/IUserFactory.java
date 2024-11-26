package com.ddd.basic.domain.model.user;

public interface IUserFactory {
    User create(String email, String name, String password);
}
