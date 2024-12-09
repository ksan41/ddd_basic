package com.ddd.basic.domain.model.circle.spec;

import com.ddd.basic.domain.model.circle.Circle;
import com.ddd.basic.domain.model.circleuser.ICircleUserRepository;
import com.ddd.basic.domain.model.user.User;
import com.ddd.basic.domain.shared.ISpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CircleFullSpecification implements ISpecification<Circle> {

    private final ICircleUserRepository circleUserRepository;

    public boolean isSatisfied(Circle circle) {
        List<User> members = circleUserRepository.findCircleMembers(circle.getId());

        long premiumUserNumber = members.stream()
                .filter(User::isPremium)
                .count();
        long circleUppserLimit = premiumUserNumber < 10 ? 30 : 50;
        return circle.countMembers() + 1 <= circleUppserLimit;
    }
}
