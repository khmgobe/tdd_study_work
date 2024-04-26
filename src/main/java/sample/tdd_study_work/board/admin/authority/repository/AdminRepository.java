package sample.tdd_study_work.board.admin.authority.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.tdd_study_work.board.admin.entity.User;

public interface AdminRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
