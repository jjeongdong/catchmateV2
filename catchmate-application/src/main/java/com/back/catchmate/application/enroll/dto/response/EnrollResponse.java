package com.back.catchmate.application.enroll.dto.response;

import com.back.catchmate.domain.enroll.model.Enroll;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EnrollResponse {
    private Long enrollId;
    private String description;
    private boolean isNew;
    private LocalDateTime requestDate;
    private ApplicantResponse applicant;

    public static EnrollResponse from(Enroll enroll) {
        return EnrollResponse.builder()
                .enrollId(enroll.getId())
                .description(enroll.getDescription())
                .requestDate(enroll.getRequestedAt())
                .isNew(enroll.isNew())
                .applicant(ApplicantResponse.from(enroll.getUser()))
                .build();
    }
}
