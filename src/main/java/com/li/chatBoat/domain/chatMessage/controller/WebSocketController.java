package com.li.chatBoat.domain.chatMessage.controller;

import com.li.chatBoat.domain.chatMessage.dto.ChatMessageDto;
import com.li.chatBoat.domain.chatRoom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
    private final ChatRoomService chatRoomService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDto sendMessage(ChatMessageDto message) {
        chatRoomService.addMessageToRoom(message.getRoomId(), message.getWriterName(), message.getContent());
        return message;
    }
}
