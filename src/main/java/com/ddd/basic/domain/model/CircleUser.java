package com.ddd.basic.domain.model;

import com.ddd.basic.domain.model.circle.Circle;
import com.ddd.basic.domain.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class CircleUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "circle_user_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "circle_id")
    private Circle circle;

    public CircleUser(User user, Circle circle) {
        this.user = user;
        this.circle = circle;
    }
}
