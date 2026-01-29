package com.back.catchmate.application.enroll.dto.response;

import com.back.catchmate.application.board.dto.response.BoardResponse;
import com.back.catchmate.application.user.dto.response.UserResponse;
import com.back.catchmate.domain.enroll.model.AcceptStatus;
import com.back.catchmate.domain.enroll.model.Enroll;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class EnrollRequestResponse {
    private Long enrollId;
    private AcceptStatus acceptStatus;
    private String description;
    private LocalDateTime requestDate;
    private BoardResponse boardResponse;

    public static EnrollRequestResponse of(Enroll enroll, boolean isBookMarked) {
        return EnrollRequestResponse.builder()
                .enrollId(enroll.getId())
                .acceptStatus(enroll.getAcceptStatus())
                .description(enroll.getDescription())
                .requestDate(enroll.getRequestedAt())
                .boardResponse(BoardResponse.of(enroll.getBoard(), isBookMarked))
                .build();
    }
}
