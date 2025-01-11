package com.li.chatBoat.domain.aiMessage.dto;

import lombok.Data;

@Data
public class AIMessageDto {
    private String writerName; // 사용자 또는 AI
    private String content;// 메시지 내용
    // 생성자 추가
    public AIMessageDto(String writerName, String content) {
        this.writerName = writerName;
        this.content = content;
    }
}