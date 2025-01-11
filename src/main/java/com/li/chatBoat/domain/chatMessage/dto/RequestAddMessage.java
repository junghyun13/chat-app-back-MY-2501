package com.li.chatBoat.domain.chatMessage.dto;

import lombok.Data;

@Data
public class RequestAddMessage {
    private String writerName;
    private String content;
}
