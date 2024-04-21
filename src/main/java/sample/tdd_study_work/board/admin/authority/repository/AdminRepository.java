package sample.tdd_study_work.board.admin.authority.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.tdd_study_work.board.admin.entity.User;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {
}
