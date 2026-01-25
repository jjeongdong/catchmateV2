package com.back.catchmate.application.enroll.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnrollCountResponse {
    private long count;

    public static EnrollCountResponse of(long count) {
        return EnrollCountResponse.builder()
                .count(count)
                .build();
    }
}
