package com.ddd.basic.domain.model.user;

import com.ddd.basic.common.constants.ExceptionMessage;
import com.ddd.basic.domain.shared.BasicEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;


@Entity
@Getter
@NoArgsConstructor
public class User extends BasicEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @Pattern(regexp = "^([가-힣]{2,12})$")
    private String name;

    @Pattern(regexp = "^(?=.*[a-zA-Z])((?=.*\\\\d)|(?=.*\\\\W)).{8,20}$")
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

    public void changePassword(String password, PasswordEncoder encoder) throws NullPointerException, IllegalArgumentException{
        if (password == null) {
            throw new NullPointerException(ExceptionMessage.NOT_VALID_USER_PASSWORD.getMessage());
        }
        if (password.length() < 6 || password.length() > 20) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_VALID_USER_PASSWORD.getMessage());
        }
        this.password = encoder.encode(password);
    }

    public Boolean isPremium() {
        return this.grade == UserGrade.PREMIUM;
    }

    public void withdrawal() {
        super.setDeleteDateNow();
    }
}
