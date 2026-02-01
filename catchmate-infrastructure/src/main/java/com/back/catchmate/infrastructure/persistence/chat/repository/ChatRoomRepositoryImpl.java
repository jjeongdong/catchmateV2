package com.back.catchmate.infrastructure.persistence.chat.repository;

import com.back.catchmate.domain.chat.model.ChatRoom;
import com.back.catchmate.domain.chat.repository.ChatRoomRepository;
import com.back.catchmate.domain.common.page.DomainPage;
import com.back.catchmate.domain.common.page.DomainPageable;
import com.back.catchmate.infrastructure.persistence.chat.entity.ChatRoomEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepository {
    private final JpaChatRoomRepository jpaChatRoomRepository;

    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        ChatRoomEntity entity = ChatRoomEntity.from(chatRoom);
        return jpaChatRoomRepository.save(entity).toModel();
    }

    @Override
    public Optional<ChatRoom> findById(Long id) {
        return jpaChatRoomRepository.findByIdWithBoard(id)
                .map(ChatRoomEntity::toModel);
    }

    @Override
    public Optional<ChatRoom> findByBoardId(Long boardId) {
        return jpaChatRoomRepository.findByBoardId(boardId)
                .map(ChatRoomEntity::toModel);
    }

    @Override
    public DomainPage<ChatRoom> findAllByUserId(Long userId, DomainPageable domainPageable) {
        Pageable pageable = PageRequest.of(
                domainPageable.getPage(),
                domainPageable.getSize(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Page<ChatRoomEntity> entityPage = jpaChatRoomRepository.findAllByUserId(userId, pageable);

        List<ChatRoom> chatRooms = entityPage.getContent().stream()
                .map(ChatRoomEntity::toModel)
                .toList();

        return new DomainPage<>(
                chatRooms,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements()
        );
    }

    @Override
    public List<ChatRoom> findAllByUserId(Long userId) {
        return jpaChatRoomRepository.findAllByUserIdList(userId).stream()
                .map(ChatRoomEntity::toModel)
                .toList();
    }

    @Override
    public boolean existsByBoardId(Long boardId) {
        return jpaChatRoomRepository.existsByBoardId(boardId);
    }

    @Override
    public void delete(ChatRoom chatRoom) {
        ChatRoomEntity entity = ChatRoomEntity.from(chatRoom);
        jpaChatRoomRepository.delete(entity);
    }
}
