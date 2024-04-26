package sample.tdd_study_work.board.admin.dto;

import lombok.Builder;
import sample.tdd_study_work.board.admin.enumeration.UserAuthorityStatus;

import java.time.LocalDate;

public record UserRead(String name, String password, LocalDate birthDay, String email, UserAuthorityStatus userAuthorityStatus) {


    @Builder
    public UserRead(String name, String password, LocalDate birthDay, String email, UserAuthorityStatus userAuthorityStatus) {
        this.name = name;
        this.password = password;
        this.birthDay = birthDay;
        this.email = email;
        this.userAuthorityStatus = userAuthorityStatus;
    }
}
