package sample.tdd_study_work.board.admin.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sample.tdd_study_work.board.admin.enumeration.UserAuthorityStatus;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class UserTest {

    @Test
    @DisplayName("관리자인지 확인한다.")
    void CheckAdmin() {

    //given
        User user = User.builder()
                .id(999L)
                .name("khm")
                .password("1234")
                .birthDay(LocalDate.now())
                .email("dr94406@naver.com")
                .userAuthorityStatus(UserAuthorityStatus.USER)
                .build();

    //when
        boolean result = user.isAdmin();

    //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아이디 또는 비밀번호가 틀렸는지 확인한다.")
    void CheckIdOrPassword() {

        //given
        User user = User.builder()
                .id(999L)
                .name("khm")
                .password("1234")
                .birthDay(LocalDate.now())
                .email(null)
                .userAuthorityStatus(UserAuthorityStatus.ADMIN)
                .build();

        //when
        boolean result = user.isValid();

        //then
        assertThat(result).isTrue();
    }

}