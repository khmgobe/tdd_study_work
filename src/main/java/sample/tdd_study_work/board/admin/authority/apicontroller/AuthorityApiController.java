package sample.tdd_study_work.board.admin.authority.apicontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import sample.tdd_study_work.board.admin.authority.service.AuthorityService;

@RestController
@RequiredArgsConstructor
public class AuthorityApiController {

    private final AuthorityService authorityService;

    public void CheckAdminPermission() {
    }
}
