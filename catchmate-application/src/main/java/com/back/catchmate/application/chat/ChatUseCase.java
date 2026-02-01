package com.back.catchmate.application.chat;

import com.back.catchmate.application.chat.dto.command.ChatMessageCommand;
import com.back.catchmate.application.chat.dto.response.ChatMessageResponse;
import com.back.catchmate.domain.chat.model.ChatMessage;
import com.back.catchmate.domain.chat.model.ChatRoom;
import com.back.catchmate.domain.chat.service.ChatMessageService;
import com.back.catchmate.domain.chat.service.ChatRoomService;
import com.back.catchmate.domain.user.model.User;
import com.back.catchmate.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatUseCase {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final UserService userService;

    /**
     * 채팅 메시지 저장
     */
    @Transactional
    public ChatMessageResponse saveMessage(ChatMessageCommand command) {
        ChatRoom chatRoom = chatRoomService.getChatRoom(command.getChatRoomId());
        User sender = userService.getUser(command.getSenderId());

        ChatMessage chatMessage = ChatMessage.createMessage(
                chatRoom,
                sender,
                command.getContent(),
                command.getMessageType()
        );

        ChatMessage savedMessage = chatMessageService.save(chatMessage);
        return ChatMessageResponse.from(savedMessage);
    }

    /**
     * 채팅방의 모든 메시지 조회
     */
    public List<ChatMessageResponse> getMessages(Long chatRoomId) {
        List<ChatMessage> messages = chatMessageService.findAllByChatRoomId(chatRoomId);
        return messages.stream()
                .map(ChatMessageResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * 채팅방의 마지막 메시지 조회
     */
    public ChatMessageResponse getLastMessage(Long chatRoomId) {
        return chatMessageService.findLastMessageByChatRoomId(chatRoomId)
                .map(ChatMessageResponse::from)
                .orElse(null);
    }

    /**
     * 사용자가 특정 채팅방에 접근 권한이 있는지 확인
     *
     * @param userId 사용자 ID
     * @param chatRoomId 채팅방 ID
     * @return 참가자이면 true, 아니면 false
     */
    public boolean canAccessChatRoom(Long userId, Long chatRoomId) {
        return chatRoomService.isUserParticipant(userId, chatRoomId);
    }
}
