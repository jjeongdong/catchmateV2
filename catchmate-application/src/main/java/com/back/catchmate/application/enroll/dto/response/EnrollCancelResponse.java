package com.back.catchmate.application.enroll.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EnrollCancelResponse {
    private Long enrollId;
    private LocalDateTime deletedAt;

    public static EnrollCancelResponse of(Long enrollId) {
        return EnrollCancelResponse.builder()
                .enrollId(enrollId)
                .deletedAt(LocalDateTime.now())
                .build();
    }
}
