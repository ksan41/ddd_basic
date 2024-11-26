package com.ddd.basic.domain.model.user;

import com.ddd.basic.common.constants.ExceptionMessage;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;


@Entity
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserGrade grade;

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
        return true;
    }
}
