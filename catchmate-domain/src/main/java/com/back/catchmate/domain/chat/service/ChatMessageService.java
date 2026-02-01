package com.back.catchmate.domain.chat.service;

import com.back.catchmate.domain.chat.model.ChatMessage;
import com.back.catchmate.domain.chat.repository.ChatMessageRepository;
import error.ErrorCode;
import error.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    public ChatMessage getChatMessage(Long messageId) {
        return chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new BaseException(ErrorCode.CHAT_MESSAGE_NOT_FOUND));
    }

    public Optional<ChatMessage> findById(Long messageId) {
        return chatMessageRepository.findById(messageId);
    }

    public List<ChatMessage> findAllByChatRoomId(Long chatRoomId) {
        return chatMessageRepository.findAllByChatRoomId(chatRoomId);
    }

    public Optional<ChatMessage> findLastMessageByChatRoomId(Long chatRoomId) {
        return chatMessageRepository.findLastMessageByChatRoomId(chatRoomId);
    }
}
