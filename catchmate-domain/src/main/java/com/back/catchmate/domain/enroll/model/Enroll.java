package com.back.catchmate.domain.enroll.model;

import com.back.catchmate.domain.board.model.Board;
import com.back.catchmate.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Enroll {
    private Long id;
    private User user;
    private Board board;
    private String description;
    private AcceptStatus acceptStatus;
    private boolean isNew;
    private LocalDateTime requestedAt;

    public static Enroll createEnroll(User user, Board board, String description) {
        return Enroll.builder()
                .user(user)
                .board(board)
                .description(description)
                .acceptStatus(AcceptStatus.PENDING)
                .isNew(true)
                .requestedAt(LocalDateTime.now())
                .build();
    }
}
