package com.ddd.basic.domain.model.invitation;

import com.ddd.basic.domain.model.circle.Circle;
import com.ddd.basic.domain.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class CircleInvitation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invitation_id")
    private Long id;

    @ManyToOne
    private Circle circle;

    @ManyToOne
    private User fromUser;
    @ManyToOne
    private User invitedUser;

    private LocalDateTime invitedDate;
    private LocalDateTime responseDate;
    private boolean isAccept;

    public CircleInvitation(Circle circle, User fromUser, User invitedUser) {
        this.circle = circle;
        this.fromUser = fromUser;
        this.invitedUser = invitedUser;
        this.invitedDate = LocalDateTime.now();
    }

    public void response(boolean isAccept) {
        this.isAccept = isAccept;
        this.responseDate = LocalDateTime.now();
    }
}
