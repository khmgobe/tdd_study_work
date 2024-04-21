package sample.tdd_study_work.board.admin.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

public String hashPassword(String password) {

    String salt = BCrypt.gensalt();
    return BCrypt.hashpw(password, salt);
}

public boolean checkPassword(String password, String hashedPassword) {
    return BCrypt.checkpw(password, hashedPassword);
    }
}