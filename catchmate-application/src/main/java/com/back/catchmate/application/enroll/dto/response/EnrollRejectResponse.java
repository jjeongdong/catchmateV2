package com.back.catchmate.application.enroll.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnrollRejectResponse {
    private Long enrollId;
    private String message;

    public static EnrollRejectResponse of(Long enrollId) {
        return EnrollRejectResponse.builder()
                .enrollId(enrollId)
                .message("직관 신청을 거절했습니다.")
                .build();
    }
}
