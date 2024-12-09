package com.ddd.basic.infrastructure;

import com.ddd.basic.domain.model.circleuser.CircleUser;
import com.ddd.basic.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaCircleUserRepository extends JpaRepository<CircleUser, Long> {

    @Query("select u " +
            "from CircleUser cu " +
            "join User u " +
            "on cu.user = u " +
            "where cu.circle.id = :circleId")
    List<User> findCircleMembers(@Param("circleId") Long circleId);
}
