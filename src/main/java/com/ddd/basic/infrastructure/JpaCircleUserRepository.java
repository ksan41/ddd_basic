package com.ddd.basic.infrastructure;

import com.ddd.basic.domain.model.circleuser.CircleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCircleUserRepository extends JpaRepository<CircleUser, Long> {
}
