package com.back.catchmate.api.chat.controller;

import com.back.catchmate.api.chat.dto.request.ChatMessageRequest;
import com.back.catchmate.application.chat.ChatUseCase;
import com.back.catchmate.application.chat.dto.response.ChatMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatUseCase chatUseCase;
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * 채팅 메시지 전송
     * 클라이언트 -> /pub/chat/message 로 메시지 전송
     * 서버 -> /sub/chat/room/{chatRoomId} 로 메시지 브로드캐스트
     */
    @MessageMapping("/chat/message")
    public void sendMessage(@Payload ChatMessageRequest request) {
        log.info("채팅 메시지 수신 - chatRoomId: {}, senderId: {}, content: {}",
                request.getChatRoomId(), request.getSenderId(), request.getContent());

        // 1. 메시지 저장
        ChatMessageResponse response = chatUseCase.saveMessage(request.toCommand());

        // 2. 해당 채팅방 구독자들에게 메시지 전송
        String destination = "/sub/chat/room/" + request.getChatRoomId();
        messagingTemplate.convertAndSend(destination, response);

        log.info("채팅 메시지 브로드캐스트 완료 - destination: {}", destination);
    }

    /**
     * 채팅방 입장
     * 클라이언트 -> /pub/chat/enter 로 입장 메시지 전송
     */
    @MessageMapping("/chat/enter")
    public void enterChatRoom(@Payload ChatMessageRequest request) {
        log.info("채팅방 입장 - chatRoomId: {}, userId: {}", request.getChatRoomId(), request.getSenderId());

        // 1. 입장 메시지 저장
        ChatMessageResponse response = chatUseCase.saveMessage(request.toCommand());

        // 2. 해당 채팅방 구독자들에게 입장 알림 전송
        String destination = "/sub/chat/room/" + request.getChatRoomId();
        messagingTemplate.convertAndSend(destination, response);

        log.info("채팅방 입장 알림 브로드캐스트 완료 - destination: {}", destination);
    }

    /**
     * 채팅방 퇴장
     * 클라이언트 -> /pub/chat/leave 로 퇴장 메시지 전송
     */
    @MessageMapping("/chat/leave")
    public void leaveChatRoom(@Payload ChatMessageRequest request) {
        log.info("채팅방 퇴장 - chatRoomId: {}, userId: {}", request.getChatRoomId(), request.getSenderId());

        // 1. 퇴장 메시지 저장
        ChatMessageResponse response = chatUseCase.saveMessage(request.toCommand());

        // 2. 해당 채팅방 구독자들에게 퇴장 알림 전송
        String destination = "/sub/chat/room/" + request.getChatRoomId();
        messagingTemplate.convertAndSend(destination, response);

        log.info("채팅방 퇴장 알림 브로드캐스트 완료 - destination: {}", destination);
    }
}
