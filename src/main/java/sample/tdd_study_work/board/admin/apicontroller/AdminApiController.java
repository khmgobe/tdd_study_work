package sample.tdd_study_work.board.admin.apicontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.tdd_study_work.board.admin.dto.UserCreate;
import sample.tdd_study_work.board.admin.dto.UserDelete;
import sample.tdd_study_work.board.admin.dto.UserUpdate;
import sample.tdd_study_work.board.admin.service.AdminService;

@RestController
@RequiredArgsConstructor
public class AdminApiController {

    private final AdminService adminService;

    @PostMapping("/api/v1/admin/hash")
    public String hashPassword(String password) {
        return adminService.hashPassword(password);
    }

    @PostMapping("/api/v1/admin")
    public void createUser(UserCreate userCreate) {

    }

    @DeleteMapping("/api/v1/admin")
    public void deleteUser(UserDelete userDelete) {

    }

    @PutMapping("/api/v1/admin")
    public void updateUser(UserUpdate userUpdate) {

    }
}
