package com.ddd.basic.domain.model.invitation;

import com.ddd.basic.domain.model.circle.Circle;
import com.ddd.basic.domain.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CircleInvitation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Circle circle;

    @ManyToOne
    private User fromUser;
    @ManyToOne
    private User invitedUser;

    public CircleInvitation(Circle circle, User fromUser, User invitedUser) {
        this.circle = circle;
        this.fromUser = fromUser;
        this.invitedUser = invitedUser;
    }
}
