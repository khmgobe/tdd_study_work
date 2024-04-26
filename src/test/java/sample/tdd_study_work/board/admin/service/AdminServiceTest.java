package sample.tdd_study_work.board.admin.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sample.tdd_study_work.board.admin.authority.repository.AdminRepository;
import sample.tdd_study_work.board.admin.dto.UserLogin;
import sample.tdd_study_work.board.admin.entity.User;
import sample.tdd_study_work.board.admin.enumeration.UserAuthorityStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class AdminServiceTest {

    @Autowired
    private AdminService adminService;


    @Test
    @DisplayName("사용자의 비밀번호가 변환되었는지 확인합니다.")
    void hashPassword() {

        //given
        String password = "";

        //when
        String hashedPassword = adminService.hashPassword(password);

        //then
        assertThat(password).isNotEqualTo(hashedPassword);

    }

    @Test
    @DisplayName("사용자의 비밀번호와 해시처리된 비밀번호를 비교하여 확인합니다.")
    void checkPassword() {

        //given
        String password = "khm123";
        String hashedPassword = adminService.hashPassword(password);

        //when
        boolean passwordMatches = adminService.checkPassword(password, hashedPassword);

        //then
        assertThat(passwordMatches).isTrue();
    }

    @Test
    @DisplayName("로그인 합니다.")
    void login() {

        //given
        UserLogin loginInfo = UserLogin.builder()
                .email("dr94406@naver.com")
                .password("$2a$10$xPoOpqzFqlK4BXjEBWqqwea1Lo3FXLuNqR8Bs6JFdaWkKyRbTONWi").build();

        //when
        String login = adminService.login(loginInfo);

        //then
        assertThat(login).isEqualTo("로그인 성공");
    }

    @Test
    @DisplayName("이메일 또는 비밀번호가 다른지 확인합니다.")
    void validateEmailOrPassword() {

        //given
        UserLogin loginInfo = UserLogin.builder()
                .email("dr94405@naver.com")
                .password("$1a$10$xPoOpqzFqlK4BXjEBWqqwea1Lo3FXLuNqR8Bs6JFdaWkKyRbTONWi").build();

        //when //then
        assertThatThrownBy(() -> adminService.login(loginInfo))
        .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이메일 또는 비밀번호가 다릅니다.");
    }

    @Test
    @DisplayName("관리자 여부를 확인합니다.")
    void validateAdminPermission() {

        //given
        User userInfo = User.builder()
                .email("dr94405@naver.com")
                .password("$1a$10$xPoOpqzFqlK4BXjEBWqqwea1Lo3FXLuNqR8Bs6JFdaWkKyRbTONWi")
                .userAuthorityStatus(UserAuthorityStatus.ADMIN).build();

        //when
        boolean valid = userInfo.isAdmin();

        //then
        assertThat(valid).isTrue();
    }
}
