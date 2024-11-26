package com.ddd.basic.domain.model.user;

import com.ddd.basic.common.constants.ExceptionMessage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserGrade grade;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.grade = UserGrade.NORMAL;
    }

    public void changeName(String name) throws NullPointerException, IllegalArgumentException{
        if (!Objects.nonNull(name)) {
            throw new NullPointerException(ExceptionMessage.NOT_VALID_USER_NAME.getMessage());
        }
        if (name.length() < 2 || name.length() > 10) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_VALID_USER_NAME.getMessage());
        }
        this.name = name;
    }

    public Boolean isPremium() {
        return this.grade == UserGrade.PREMIUM;
    }
}
