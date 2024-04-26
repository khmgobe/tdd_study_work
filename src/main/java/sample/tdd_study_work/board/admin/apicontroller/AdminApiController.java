package sample.tdd_study_work.board.admin.apicontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sample.tdd_study_work.board.admin.authority.enumeration.BoardAuthorityStatus;
import sample.tdd_study_work.board.admin.dto.*;
import sample.tdd_study_work.board.admin.service.AdminService;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminApiController {

    private final AdminService adminService;

    @PostMapping("/api/v1/admin/login")
    public void login(@RequestBody UserLogin userLogin) {
         adminService.login(userLogin);
    }


    @PostMapping("/api/v1/admin")
    public Long create(@RequestBody UserCreate userCreate) {
        return adminService.create(userCreate);
    }

    @DeleteMapping("/api/v1/admin/{id}")
    public void delete(@PathVariable Long id) {
        adminService.delete(id);

    }
    @PatchMapping("/api/v1/admin")
    public void update(@RequestBody UserUpdate userUpdate) {
        adminService.update(userUpdate);
    }

    @GetMapping("/api/v1/admin")
    public List<UserRead> findAll() {
        return adminService.findAll();
    }

    @PatchMapping("/api/v1/admin/reset/{id}")
    public void reset(@PathVariable Long id) {
        adminService.reset(id);
    }

    @PatchMapping("/api/v1/admin/permission/{id}")
    public void updatePermission(@PathVariable Long id, @RequestBody BoardAuthorityStatus boardAuthorityStatus) {
        adminService.updatePermission(id, boardAuthorityStatus);
    }
}
