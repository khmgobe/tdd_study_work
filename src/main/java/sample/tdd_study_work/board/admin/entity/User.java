package sample.tdd_study_work.board.admin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import sample.tdd_study_work.board.admin.authority.enumeration.BoardAuthorityStatus;
import sample.tdd_study_work.board.admin.dto.UserRead;
import sample.tdd_study_work.board.admin.dto.UserUpdate;
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

    private Integer permission;

    @Builder
    private User(Long id, String name, String password, LocalDate birthDay, String email, UserAuthorityStatus userAuthorityStatus, Integer permission) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.birthDay = birthDay;
        this.email = email;
        this.userAuthorityStatus = userAuthorityStatus;
        this.permission = permission;
    }

    public Long create() {
        return id;
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

    public void updateUser (UserUpdate userUpdate) {
        this.email = userUpdate.email();
        this.name = userUpdate.name();
        this.password = hashPassword(userUpdate.password());
    }

    public String resetPassword() {
        return String.valueOf(birthDay);
    }

    public void updatePassword(String changePassword) {
        this.password = hashPassword(changePassword);
    }

    public String hashPassword(String password) {

        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public int updatePermission(BoardAuthorityStatus boardAuthorityStatus) {
        return this.permission = boardAuthorityStatus.getStatus();
    }
}