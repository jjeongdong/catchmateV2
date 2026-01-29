package com.back.catchmate.application.user.dto.response;

import com.back.catchmate.application.club.dto.response.ClubResponse;
import com.back.catchmate.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse {
    private Long userId;
    private String nickName;
    private String email;
    private String profileImageUrl;

    private char gender;
    private LocalDate birthDate;
    private String watchStyle;

    private ClubResponse club;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .gender(user.getGender())
                .nickName(user.getNickName())
                .club(ClubResponse.from(user.getClub()))
                .birthDate(user.getBirthDate())
                .watchStyle(user.getWatchStyle())
                .build();
    }
}
