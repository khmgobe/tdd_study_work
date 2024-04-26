package sample.tdd_study_work.board.admin.dto;

import lombok.Builder;

public record UserLogin (String email, String password) {


    @Builder
    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
