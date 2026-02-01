package com.back.catchmate.domain.chat.service;

import com.back.catchmate.domain.chat.model.ChatRoom;
import com.back.catchmate.domain.chat.repository.ChatRoomRepository;
import error.ErrorCode;
import error.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom getChatRoom(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new BaseException(ErrorCode.CHATROOM_NOT_FOUND));
    }

    public Optional<ChatRoom> findById(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId);
    }

    public Optional<ChatRoom> findByBoardId(Long boardId) {
        return chatRoomRepository.findByBoardId(boardId);
    }

    public ChatRoom save(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    // 사용자 기준으로 참가중인 채팅방 리스트 조회
    public List<ChatRoom> findAllByUserId(Long userId) {
        return chatRoomRepository.findAllByUserId(userId);
    }

    // 특정 채팅방에 사용자가 참여중인지 확인
    public boolean isUserParticipant(Long userId, Long chatRoomId) {
        List<ChatRoom> rooms = findAllByUserId(userId);
        return rooms.stream().anyMatch(r -> r.getId() != null && r.getId().equals(chatRoomId));
    }
}
