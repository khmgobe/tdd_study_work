package sample.tdd_study_work.board.admin.dto;

import lombok.Builder;

public record UserUpdate (String email, String name, String password) {


    @Builder
    public UserUpdate(String email, String name, String password) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}