package com.back.catchmate.api.board.dto.request;

import com.back.catchmate.application.board.dto.command.BoardCreateOrUpdateCommand;
import com.back.catchmate.application.board.dto.command.GameCreateCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardCreateOrUpdateRequest {
    @Positive
    private Long boardId;

    private String title;

    private String content;

    private Integer maxPerson;

    private Long cheerClubId;

    private String preferredGender;

    private List<String> preferredAgeRange;

    @NotNull(message = "임시저장 여부는 필수입니다.")
    private Boolean completed;

    private GameRequest gameRequest;

    @Getter
    @NoArgsConstructor
    public static class GameRequest {
        private Long homeClubId;
        private Long awayClubId;
        private LocalDateTime gameStartDate;
        private String location;

        public GameCreateCommand toCommand() {
            return GameCreateCommand.builder()
                    .homeClubId(homeClubId)
                    .awayClubId(awayClubId)
                    .gameStartDate(gameStartDate)
                    .location(location)
                    .build();
        }
    }

    public BoardCreateOrUpdateCommand toCommand() {
        return BoardCreateOrUpdateCommand.builder()
                .boardId(boardId)
                .title(title)
                .content(content)
                .maxPerson(maxPerson != null ? maxPerson : 0)
                .cheerClubId(cheerClubId)
                .preferredGender(preferredGender)
                .preferredAgeRange(preferredAgeRange)
                .gameCreateCommand(gameRequest != null ? gameRequest.toCommand() : null)
                .completed(completed)
                .build();
    }

    public BoardCreateOrUpdateCommand toCommand(Long boardId) {
        return BoardCreateOrUpdateCommand.builder()
                .boardId(boardId)
                .title(title)
                .content(content)
                .maxPerson(maxPerson)
                .cheerClubId(cheerClubId)
                .preferredGender(preferredGender)
                .preferredAgeRange(preferredAgeRange)
                .gameCreateCommand(gameRequest != null ? gameRequest.toCommand() : null)
                .completed(completed)
                .build();
    }
}
