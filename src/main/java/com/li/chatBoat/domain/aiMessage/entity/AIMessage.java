package com.li.chatBoat.domain.aiMessage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writerName; // 사용자 또는 AI

    private String content;    // 메시지 내용

    private LocalDateTime createdAt; // 메시지 생성 시간

    // 메시지를 생성할 때 자동으로 생성 시간을 세팅
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}

