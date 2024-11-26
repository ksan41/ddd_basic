package com.ddd.basic.infrastructure.user;

import com.ddd.basic.domain.model.user.IUserRepository;
import com.ddd.basic.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, IUserRepository {
}
