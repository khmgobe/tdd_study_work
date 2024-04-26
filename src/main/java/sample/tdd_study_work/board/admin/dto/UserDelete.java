package sample.tdd_study_work.board.admin.dto;

import java.time.LocalDate;

public record UserDelete (Long id, String name, String password, LocalDate birthDay, String email) {
}
