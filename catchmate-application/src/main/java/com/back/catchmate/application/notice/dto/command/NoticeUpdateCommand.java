package com.back.catchmate.application.notice.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeUpdateCommand {
    private String title;
    private String content;
}
