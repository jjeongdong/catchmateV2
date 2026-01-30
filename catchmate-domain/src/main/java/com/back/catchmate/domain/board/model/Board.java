package com.back.catchmate.domain.board.model;

import com.back.catchmate.domain.club.model.Club;
import com.back.catchmate.domain.game.model.Game;
import com.back.catchmate.domain.user.model.User;
import error.ErrorCode;
import error.exception.BaseException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Board {
    private Long id;
    private String title;
    private String content;
    private Integer maxPerson;
    private int currentPerson;
    private User user;
    private Club cheerClub;
    private Game game;
    private String preferredGender;
    private String preferredAgeRange;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime liftUpDate;
    private LocalDateTime deletedAt;

    public static Board createBoard(String title, String content, int maxPerson, User user,
                                    Club cheerClub, Game game, String preferredGender,
                                    List<String> preferredAgeRange, boolean completed) {

        Board board = Board.builder()
                .title(title)
                .content(content)
                .maxPerson(maxPerson)
                .currentPerson(1)
                .user(user)
                .cheerClub(cheerClub)
                .game(game)
                .preferredGender(preferredGender)
                .preferredAgeRange(String.join(",", preferredAgeRange))
                .completed(completed)
                .createdAt(LocalDateTime.now())
                .liftUpDate(LocalDateTime.now())
                .build();

        board.validateForPublish();

        return board;
    }

    public void updateBoard(String title, String content, int maxPerson,
                            Club cheerClub, Game game, String preferredGender,
                            List<String> preferredAgeRange, boolean completed) {
        validateUpdatable();

        this.title = title;
        this.content = content;
        this.maxPerson = maxPerson;
        this.cheerClub = cheerClub;
        this.game = game;
        this.preferredGender = preferredGender;
        this.preferredAgeRange = String.join(",", preferredAgeRange);
        this.completed = completed;

        validateForPublish();
    }

    public void validateForPublish() {
        if (!this.completed) {
            return;
        }

        if (title == null || title.isBlank()) {
            throw new BaseException(ErrorCode.BOARD_TITLE_MISSING);
        }
        if (content == null || content.isBlank()) {
            throw new BaseException(ErrorCode.BOARD_CONTENT_MISSING);
        }
        if (maxPerson == null) {
            throw new BaseException(ErrorCode.BOARD_MAX_PERSON_MISSING);
        }
        if (cheerClub == null) {
            throw new BaseException(ErrorCode.BOARD_CHEER_CLUB_MISSING);
        }
        if (game == null) {
            throw new BaseException(ErrorCode.BOARD_GAME_MISSING);
        }
    }

    private void validateUpdatable() {
        if (this.currentPerson >= 2) {
            throw new BaseException(ErrorCode.BOARD_CANNOT_UPDATE_AFTER_ENROLL);
        }
    }

    public void updateLiftUpDate(LocalDateTime liftUpDate) {
        this.liftUpDate = liftUpDate;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void increaseCurrentPerson() {
        if (this.currentPerson >= this.maxPerson) {
            throw new BaseException(ErrorCode.FULL_PERSON);
        }
        this.currentPerson++;
    }
}
