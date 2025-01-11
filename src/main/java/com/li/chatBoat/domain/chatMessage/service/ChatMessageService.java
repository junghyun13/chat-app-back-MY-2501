package com.li.chatBoat.domain.chatMessage.service;

import com.li.chatBoat.domain.chatMessage.entity.ChatMessage;
import com.li.chatBoat.domain.chatMessage.repository.ChatMessageRepository;
import com.li.chatBoat.domain.chatRoom.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    public void create(ChatRoom chatRoom, String writerName, String content) {
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .writerName(writerName)
                .content(content)
                .build();
        chatMessageRepository.save(chatMessage);
    }
}
/*
private final ChatMessageRepository chatMessageRepository;
    public void create(ChatRoom chatRoom, String writerName, String content) {
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .writerName(writerName)
                .content(content)
                .build();
        chatMessageRepository.save(chatMessage);
    }
    // 특정 채팅방의 메시지 목록 조회
    public List<ChatMessage> getMessages(Long roomId, Long afterChatMessageId) {
        if (afterChatMessageId == -1) {
            return chatMessageRepository.findByRoomId(roomId);
        }
        return chatMessageRepository.findByRoomIdAndIdGreaterThan(roomId, afterChatMessageId);
    }
 */