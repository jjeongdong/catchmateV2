package com.back.catchmate.application.enroll.dto.response;

import com.back.catchmate.application.user.dto.response.UserResponse;
import com.back.catchmate.domain.enroll.model.AcceptStatus;
import com.back.catchmate.domain.enroll.model.Enroll;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EnrollResponse {
    private Long enrollId;
    private AcceptStatus acceptStatus;
    private String description;
    private LocalDateTime requestDate;
    private UserResponse applicant;

    public static EnrollResponse from(Enroll enroll) {
        return EnrollResponse.builder()
                .enrollId(enroll.getId())
                .acceptStatus(enroll.getAcceptStatus())
                .description(enroll.getDescription())
                .requestDate(enroll.getRequestedAt())
                .applicant(UserResponse.from(enroll.getUser()))
                .build();
    }
}
