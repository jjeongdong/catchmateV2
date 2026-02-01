package com.back.catchmate.domain.chat.model;

public enum MessageType {
    TEXT,       // 일반 텍스트 메시지
    IMAGE,      // 이미지 메시지
    SYSTEM,     // 시스템 메시지 (입장, 퇴장 등)
    ENTER,      // 채팅방 입장
    LEAVE       // 채팅방 퇴장
}
