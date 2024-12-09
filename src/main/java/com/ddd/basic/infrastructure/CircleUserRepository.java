package com.ddd.basic.infrastructure;

import com.ddd.basic.domain.model.circleuser.CircleUser;
import com.ddd.basic.domain.model.circleuser.ICircleUserRepository;
import com.ddd.basic.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CircleUserRepository implements ICircleUserRepository {

    private final JpaCircleUserRepository jpaCircleUserRepository;

    @Override
    public CircleUser save(CircleUser circleUser) {
        return jpaCircleUserRepository.save(circleUser);
    }

    @Override
    public List<User> findCircleMembers(Long circleId) {
        return jpaCircleUserRepository.findCircleMembers(circleId);
    }


}
