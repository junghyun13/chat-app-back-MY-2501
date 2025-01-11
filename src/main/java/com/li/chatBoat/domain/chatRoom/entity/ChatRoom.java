package com.li.chatBoat.domain.chatRoom.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.li.chatBoat.domain.chatMessage.entity.ChatMessage;
import com.li.chatBoat.global.jpa.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)
public class ChatRoom  extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // 부모 엔터티
    private List<ChatMessage> chatMessages = new ArrayList<>();

    public ChatMessage writeMessage(String writerName, String content) {
        ChatMessage chatMessage = ChatMessage
                .builder()
                .chatRoom(this)
                .writerName(writerName)
                .content(content)
                .build();
        chatMessages.add(chatMessage);
        return chatMessage;
    }


    public ChatRoom () {
        String name;
    }
}