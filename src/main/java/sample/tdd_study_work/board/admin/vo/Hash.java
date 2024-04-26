package sample.tdd_study_work.board.admin.vo;

import org.mindrot.jbcrypt.BCrypt;

public class Hash {

    String plainPassword = "userPassword123"; // 사용자가 입력한 비밀번호

    // 비밀번호를 해시 처리하여 저장
    String hashedPassword = hashPassword(plainPassword);

    // 사용자가 로그인 시 입력한 비밀번호를 해시 처리된 비밀번호와 비교
    boolean passwordMatch = checkPassword(plainPassword, hashedPassword);

// 비밀번호를 해시 처리하는 메서드
    public static String hashPassword(String plainPassword) {
    // 해시 처리할 때 솔트를 생성하여 함께 사용
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(plainPassword, salt);
}

// 해시 처리된 비밀번호와 입력된 비밀번호를 비교하는 메서드
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
