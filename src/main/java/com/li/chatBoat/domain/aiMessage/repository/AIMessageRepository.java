package com.li.chatBoat.domain.aiMessage.repository;

import com.li.chatBoat.domain.aiMessage.entity.AIMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AIMessageRepository extends JpaRepository<AIMessage, Long> {
}