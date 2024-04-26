package sample.tdd_study_work.board.user.apicontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.tdd_study_work.board.admin.dto.UserLogin;
import sample.tdd_study_work.board.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/v1/user/login")
    public void login (@RequestBody UserLogin userLogin) {
        userService.login(userLogin);
    }
}
