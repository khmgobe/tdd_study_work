package sample.tdd_study_work.board.admin.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdminServiceTest {

    private final AdminService adminService = new AdminService();

    @DisplayName("사용자의 비밀번호가 변환되었는지 확인합니다.")
    @Test
    void hashPassword() {

        //given
        String password = "";

        //when
        String hashedPassword = adminService.hashPassword(password);


        //then
        assertThat(password).isNotEqualTo(hashedPassword);

    }

    @DisplayName("사용자의 비밀번호와 해시처리된 비밀번호를 비교하여 확인합니다.")
    @Test
    void checkPassword() {

        //given
        String password = "khm123";
        String hashedPassword = adminService.hashPassword(password);

        //when
        boolean passwordMatches = adminService.checkPassword(password, hashedPassword);

        //then
        assertThat(passwordMatches).isTrue();
    }
}
