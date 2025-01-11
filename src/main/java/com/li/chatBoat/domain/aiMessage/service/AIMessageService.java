package com.li.chatBoat.domain.aiMessage.service;

import com.li.chatBoat.domain.aiMessage.dto.AIMessageDto;
import com.li.chatBoat.domain.aiMessage.entity.AIMessage;
import com.li.chatBoat.domain.aiMessage.repository.AIMessageRepository;
import com.li.chatBoat.domain.chatMessage.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AIMessageService {
    private final AIMessageRepository aiMessageRepository;

    // 메시지 저장
    public AIMessage saveMessage(String writerName, String content) {
        AIMessage message = AIMessage.builder()
                .writerName(writerName)
                .content(content)
                .build();
        return aiMessageRepository.save(message);
    }

    public List<AIMessageDto> getAllMessages() {
        return aiMessageRepository.findAll()
                .stream()
                .map(message -> new AIMessageDto(
                        message.getWriterName(),
                        message.getContent()
                ))
                .collect(Collectors.toList());
    }
}
