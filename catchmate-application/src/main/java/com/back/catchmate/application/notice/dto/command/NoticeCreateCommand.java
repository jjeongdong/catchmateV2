package com.back.catchmate.application.notice.dto.command;

import com.back.catchmate.domain.notice.model.Notice;
import com.back.catchmate.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeCreateCommand {
    private String title;
    private String content;
}
