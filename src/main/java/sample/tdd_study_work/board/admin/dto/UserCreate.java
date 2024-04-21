package sample.tdd_study_work.board.admin.dto;

import java.time.LocalDate;

public record UserCreate (String name, String password, LocalDate birthDay, String email) {
}
