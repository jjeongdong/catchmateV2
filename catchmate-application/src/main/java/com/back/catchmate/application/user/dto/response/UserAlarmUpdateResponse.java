package com.back.catchmate.application.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import user.enums.AlarmType;

@Getter
@Builder
@AllArgsConstructor
public class UserAlarmUpdateResponse {
    private AlarmType alarmType;
    private boolean isEnabled;

    public static UserAlarmUpdateResponse of(AlarmType alarmType, boolean isEnabled) {
        return UserAlarmUpdateResponse.builder()
                .alarmType(alarmType)
                .isEnabled(isEnabled)
                .build();
    }
}
