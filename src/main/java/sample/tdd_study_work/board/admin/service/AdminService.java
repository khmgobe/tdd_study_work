package sample.tdd_study_work.board.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import sample.tdd_study_work.board.admin.authority.enumeration.BoardAuthorityStatus;
import sample.tdd_study_work.board.admin.authority.repository.AdminRepository;
import sample.tdd_study_work.board.admin.dto.*;
import sample.tdd_study_work.board.admin.entity.User;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;

    public String hashPassword(String password) {

        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public Long create(UserCreate userCreate) {

        User user = userCreate.dtoToEntity();

        checkEmail(userCreate.email());

        adminRepository.save(user);

        return user.create();
    }

    public void update(UserUpdate userUpdate) {

        User user = adminRepository.findByEmail(userUpdate.email());
        checkEmail(userUpdate.email());
        user.updateUser(userUpdate);
        adminRepository.save(user);
    }

    public void delete(Long id) {

        adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원은 삭제할 수 없습니다."));

        adminRepository.deleteById(id);
    }

    public List<UserRead> findAll() {
        List<User> user = adminRepository.findAll();
        return user.stream()
                .filter((value -> !value.isAdmin()))
                .map(User::of2).collect(Collectors.toList());
    }

    public String login(UserLogin userLogin) {

        User user = adminRepository.findByEmailAndPassword(userLogin.email(), userLogin.password());
        validator(user);

        return "로그인 성공";
    }

    public static void validator(User user) {
        if(user == null || !user.isValid()) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 다릅니다.");
        }

        if(user.isAdmin()) {
            throw new IllegalArgumentException("관리자 권한이 없습니다. 관리자 페이지에 진입할 수 없습니다.");
        }
    }

    private void checkEmail(String email) {
        if(adminRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }
    }

    public void reset(Long id) {

        User user = adminRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원의 비밀번호는 초기화할 수 없습니다."));

        String randomPassword = user.resetPassword();

        user.updatePassword(randomPassword);

        adminRepository.save(user);
    }

    public int updatePermission(Long id, BoardAuthorityStatus boardAuthorityStatus) {

        User user = adminRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원에게 권한을 부여할 수 없습니다."));

        int value = user.updatePermission(boardAuthorityStatus);

        adminRepository.save(user);

        return value;
    }
}