package com.li.chatBoat.domain.chatMessage.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@ToString
public class ChatMessageDto {
    private Long roomId;
    private String writerName;
    private String content;
    private String createdAt;
}
