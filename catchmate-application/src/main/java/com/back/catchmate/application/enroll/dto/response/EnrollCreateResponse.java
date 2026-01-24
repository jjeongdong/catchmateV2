package com.back.catchmate.application.enroll.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EnrollCreateResponse {
    private Long enrollId;
    private LocalDateTime requestAt;

    public static EnrollCreateResponse of(Long enrollId) {
        return EnrollCreateResponse.builder()
                .enrollId(enrollId)
                .requestAt(LocalDateTime.now())
                .build();
    }
}
