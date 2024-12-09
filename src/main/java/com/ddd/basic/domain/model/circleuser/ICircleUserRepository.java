package com.ddd.basic.domain.model.circleuser;

import com.ddd.basic.domain.model.user.User;

import java.util.List;

public interface ICircleUserRepository {

    CircleUser save(CircleUser circleUser);

    List<User> findCircleMembers(Long circleId);
}
