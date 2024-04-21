package sample.tdd_study_work.board.admin.authority.enumeration;

import lombok.Getter;
@Getter
public enum BoardAuthorityStatus {

    WRITE(1),
    READ(2),
    MOVE(4);

    @Getter
    private int status;

    BoardAuthorityStatus(int status) {
        this.status = status;
    }

    private static BoardAuthorityStatus valueOf(Integer status) {
        return switch (status) {
            case 1 -> BoardAuthorityStatus.WRITE;
            case 2 -> BoardAuthorityStatus.READ;
            case 4 -> BoardAuthorityStatus.MOVE;
            default -> throw new AssertionError("상태 상수 입력 오류");
        };

        }
    }
