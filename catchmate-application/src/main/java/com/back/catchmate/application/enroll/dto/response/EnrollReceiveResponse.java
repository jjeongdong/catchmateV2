package com.back.catchmate.application.enroll.dto.response;

import com.back.catchmate.application.board.dto.response.BoardResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollReceiveResponse {
    private BoardResponse boardResponse;
    private List<EnrollResponse> enrollResponses;

    public static EnrollReceiveResponse of(BoardResponse boardResponse, List<EnrollResponse> enrollResponses) {
        return EnrollReceiveResponse.builder()
                .boardResponse(boardResponse)
                .enrollResponses(enrollResponses)
                .build();
    }
}
