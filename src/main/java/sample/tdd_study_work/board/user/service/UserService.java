package sample.tdd_study_work.board.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.tdd_study_work.board.admin.dto.UserLogin;
import sample.tdd_study_work.board.admin.entity.User;
import sample.tdd_study_work.board.user.repository.UserRepository;
import static sample.tdd_study_work.board.admin.service.AdminService.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String login(UserLogin userLogin) {
        User user = userRepository.findByEmailAndPassword(userLogin.email(), userLogin.password());
        validator(user);
        return "로그인 성공";
    }
}
