package com.back.catchmate.application.notification.dto.response;

import com.back.catchmate.domain.board.model.Board;
import com.back.catchmate.domain.game.model.Game;
import com.back.catchmate.domain.notification.model.Notification;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import user.enums.AlarmType;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationResponse {
    private final Long id;
    private final String title;
    private final String body;
    private final AlarmType alarmType;
    private final boolean isRead;
    private final LocalDateTime createdAt;
    private final String senderProfileImageUrl;
    private final String gameInfo;

    public static NotificationResponse from(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .body(notification.getBody())
                .alarmType(notification.getType())
                .isRead(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .senderProfileImageUrl(notification.getSender() != null ?
                        notification.getSender().getProfileImageUrl() : null)
                .gameInfo(notification.getBoard() != null ?
                        formatGameInfo(notification.getBoard()) : null)
                .build();
    }

    private static String formatGameInfo(Board board) {
        Game game = board.getGame();
        return String.format("%s · %s · %s vs %s",
                game.getGameStartDate().toLocalDate(),
                game.getLocation(),
                game.getHomeClub().getName(),
                game.getAwayClub().getName()
        );
    }
}
