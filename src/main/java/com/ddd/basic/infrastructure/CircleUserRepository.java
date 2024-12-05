package com.ddd.basic.infrastructure;

import com.ddd.basic.domain.model.circleuser.CircleUser;
import com.ddd.basic.domain.model.circleuser.ICircleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CircleUserRepository implements ICircleUserRepository {

    private final JpaCircleUserRepository jpaCircleUserRepository;

    @Override
    public CircleUser save(CircleUser circleUser) {
        return jpaCircleUserRepository.save(circleUser);
    }
}
