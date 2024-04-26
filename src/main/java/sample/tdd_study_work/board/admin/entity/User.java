package sample.tdd_study_work.board.admin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import sample.tdd_study_work.board.admin.dto.UserCreate;
import sample.tdd_study_work.board.admin.dto.UserRead;
import sample.tdd_study_work.board.admin.enumeration.UserAuthorityStatus;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private LocalDate birthDay;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserAuthorityStatus userAuthorityStatus;

    @Builder
    private User(Long id, String name, String password, LocalDate birthDay, String email, UserAuthorityStatus userAuthorityStatus) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.birthDay = birthDay;
        this.email = email;
        this.userAuthorityStatus = userAuthorityStatus;
    }

    public UserCreate of() {
        return UserCreate.builder().
                name(name).password(password).
                birthDay(birthDay).
                email(email).
                userAuthorityStatus(userAuthorityStatus).
                build();
    }

    public UserRead of2() {
        return UserRead.builder().
                name(name).password(password).
                birthDay(birthDay).
                email(email).
                userAuthorityStatus(userAuthorityStatus).
                build();
    }

    public boolean isAdmin() {
        if(userAuthorityStatus.equals(UserAuthorityStatus.ADMIN)) {
            return true;
        }
        return false;
    }

    public boolean isValid() {
        if(email != null && password != null) {
            return true;
        }
        return false;
    }
}