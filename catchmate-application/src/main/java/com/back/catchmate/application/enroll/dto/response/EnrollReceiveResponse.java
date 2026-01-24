package com.back.catchmate.application.enroll.dto.response;

import com.back.catchmate.application.board.dto.response.BoardResponse;
import com.back.catchmate.application.common.PagedResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollReceiveResponse {
    private BoardResponse boardResponse;
    private PagedResponse<EnrollResponse> enrollResponses;

    public static EnrollReceiveResponse of(BoardResponse boardResponse, PagedResponse<EnrollResponse> enrollResponses) {
        return EnrollReceiveResponse.builder()
                .boardResponse(boardResponse)
                .enrollResponses(enrollResponses)
                .build();
    }
}
