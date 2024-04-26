package sample.tdd_study_work.board.admin.dto;

import lombok.Builder;
import org.mindrot.jbcrypt.BCrypt;
import sample.tdd_study_work.board.admin.entity.User;
import sample.tdd_study_work.board.admin.enumeration.UserAuthorityStatus;

import java.time.LocalDate;

public record UserCreate (String name, String password, LocalDate birthDay, String email, UserAuthorityStatus userAuthorityStatus) {

    public String hashPassword(String password) {

        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public User dtoToEntity() {
        return User.builder().name(name)
                .password(hashPassword(password))
                .birthDay(birthDay).email(email)
                .userAuthorityStatus(userAuthorityStatus).build();
    }

    @Builder
    public UserCreate(String name, String password, LocalDate birthDay, String email, UserAuthorityStatus userAuthorityStatus) {
        this.name = name;
        this.password = password;
        this.birthDay = birthDay;
        this.email = email;
        this.userAuthorityStatus = userAuthorityStatus;
    }
}
