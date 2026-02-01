package com.back.catchmate.api.chat.controller;

import com.back.catchmate.application.chat.ChatUseCase;
import com.back.catchmate.application.chat.dto.response.ChatMessageResponse;
import com.back.catchmate.global.annotation.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "[사용자] 채팅 API")
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatRestController {
    private final ChatUseCase chatUseCase;

    @GetMapping("/rooms/{chatRoomId}/messages")
    @Operation(summary = "채팅 메시지 목록 조회", description = "특정 채팅방의 모든 메시지를 조회합니다.")
    public ResponseEntity<List<ChatMessageResponse>> getMessages(@AuthUser Long userId,
                                                                 @PathVariable Long chatRoomId) {
        List<ChatMessageResponse> messages = chatUseCase.getMessages(chatRoomId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/rooms/{chatRoomId}/messages/last")
    @Operation(summary = "마지막 메시지 조회", description = "특정 채팅방의 마지막 메시지를 조회합니다.")
    public ResponseEntity<ChatMessageResponse> getLastMessage(@AuthUser Long userId,
                                                              @PathVariable Long chatRoomId) {
        ChatMessageResponse response = chatUseCase.getLastMessage(chatRoomId);
        if (response == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
