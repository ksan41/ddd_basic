package com.ddd.basic.infrastructure.user;

import com.ddd.basic.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
