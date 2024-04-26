package sample.tdd_study_work.board.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sample.tdd_study_work.board.admin.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
}
