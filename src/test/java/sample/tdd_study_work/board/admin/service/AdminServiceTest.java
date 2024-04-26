package sample.tdd_study_work.board.admin.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sample.tdd_study_work.board.admin.authority.enumeration.BoardAuthorityStatus;
import sample.tdd_study_work.board.admin.dto.UserCreate;
import sample.tdd_study_work.board.admin.dto.UserLogin;
import sample.tdd_study_work.board.admin.dto.UserRead;
import sample.tdd_study_work.board.admin.dto.UserUpdate;
import sample.tdd_study_work.board.admin.entity.User;
import sample.tdd_study_work.board.admin.enumeration.UserAuthorityStatus;

import java.util.List;

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

    @Test
    @DisplayName("사용자 목록 확인")
    void findAllUser() {

        //given
        List<UserRead> userList = List.of(
                createData("dr94406@naver.com", "khm"),
                createData("dr94408@naver.com", "형민이!"));

        //when
        List<UserRead> allUserList = adminService.findAll();

        //then 쓰레기 코드. 테스트 강의 내용이 기억이 안남 ;; ㅡㅡ;; 앗 ;;
        assertThat(userList.get(0).name().equals(allUserList.get(0).name()));

    }

    @Test
    @DisplayName("사용자 생성 시 이미 있는 이메일인지 확인하고 맞다면 뿜 ! ")
    void createUser() {

        //given
        UserCreate userCreate = UserCreate
                .builder()
                .name("김형민")
                .email("dr94409@naver.com")
                .password("rkdcjfdydtkdughdhk!")
                .build();

        //when //then
        assertThatThrownBy(() -> adminService.create(userCreate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 가입된 이메일이 없음!");
    }

    @Test
    @DisplayName("사용자 수정")
    void updateUser() {

        //given
        UserUpdate userUpdate = UserUpdate
                .builder()
                .name("김형민")
                .email("dr94406@naver.com")
                .password("rkdcjfdydtkdughdhk!")
                .build();

        //when //then
        assertThatThrownBy(() -> adminService.update(userUpdate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 가입된 이메일입니다.");
    }

    @Test
    @DisplayName("사용자 삭제, 존재하지 않는 회원을 삭제하려 하면 뿌밋이 발생한다 뿌밋 ! ")
    void deleteUser() {

        //given
        Long id = 1L;

        //when //then
        assertThatThrownBy(() -> adminService.delete(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 회원은 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("사용자의 비밀번호를 생일로 초기화합니다. 존재하지 않는 회원의 비밀번호를 초기화 하면 뿌밋이 발생한다. 뿌밋! ")
    void resetUserPassword() {

        //given
        Long id = 1L;

        //when //then
        assertThatThrownBy(() -> adminService.reset(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 회원의 비밀번호는 초기화할 수 없습니다.");
    }

    @Test
    @DisplayName("사용자에게 권한을 부여합니다. 권한 대상이 없다면 뿌밋이 발생한다 뿌밋!")
    void updatePermission() {

        //given
        Long id = 1L;

        //when
        int value = adminService.updatePermission(id, BoardAuthorityStatus.MOVE);

        //then
        assertThat(value).isEqualTo(4);
    }

    private UserRead createData(String email, String name) {
        return UserRead
                .builder()
                .email(email)
                .name(name)
                .build();
    }
}
