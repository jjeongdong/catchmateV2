package com.back.catchmate.domain.notice.model;

import com.back.catchmate.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Notice {
    private Long id;
    private User writer;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public static Notice createNotice(User writer, String title, String content) {
        return Notice.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
