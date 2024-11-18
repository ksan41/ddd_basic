package com.ddd.basic.domain.circle;

import com.ddd.basic.common.constants.ExceptionMessage;
import com.ddd.basic.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
public class Circle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "circle_id")
    private Long id;

    @Column(name = "circle_name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
    @OneToMany
    @JoinColumn()
    private List<User> members;

    public Circle(String name, User owner, List<User> members) throws IllegalArgumentException{
        if (!Objects.nonNull(name) || name.length() < 3 || name.length() > 20) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_VALID_CIRCLE_NAME.getMessage());
        }
        this.name = name;
        this.owner = owner;
        this.members = members;
    }

    public void join(User user) throws IllegalArgumentException{
        if (!Objects.nonNull(user)) throw new NullPointerException(ExceptionMessage.NOT_FOUND_USER.getMessage());
        if (isFull()) {
            throw new IllegalArgumentException(ExceptionMessage.FULL_CIRCLE_MEMBERS.getMessage());
        }
        members.add(user);
    }

    public boolean isFull() {
        return members.size() >= 29;
    }
}
