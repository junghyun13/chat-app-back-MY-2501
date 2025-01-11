package com.li.chatBoat.domain.chatMessage.repository;

import com.li.chatBoat.domain.chatMessage.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
/*
List<ChatMessage> findByRoomId(Long roomId);

    List<ChatMessage> findByRoomIdAndIdGreaterThan(Long roomId, Long afterChatMessageId);
 */