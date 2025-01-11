package com.li.chatBoat.domain.chatMessage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.li.chatBoat.domain.chatRoom.entity.ChatRoom;
import com.li.chatBoat.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)
public class ChatMessage extends BaseEntity {
    private String writerName;
    private String content;
    @ManyToOne //메세지 여러개에 방 1개
    @JsonBackReference // 자식 엔터티
    private ChatRoom chatRoom;
    //ORM object relational mapping은
    public ChatMessage () {

    }


}
