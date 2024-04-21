package sample.tdd_study_work.board.admin.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserUpdate(String name, String password, LocalDate birthDay, String email) {

}