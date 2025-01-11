package com.li.chatBoat.global.initData;

import com.li.chatBoat.domain.chatMessage.entity.ChatMessage;
import com.li.chatBoat.domain.chatMessage.service.ChatMessageService;
import com.li.chatBoat.domain.chatRoom.entity.ChatRoom;
import com.li.chatBoat.domain.chatRoom.service.ChatRoomService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.stream.IntStream;

@Configuration
@Profile("!prod")
public class NotProd {

    @Bean
    public ApplicationRunner applicationRunner(ChatRoomService chatRoomService, ChatMessageService chatMessageService) {
        return args -> {
            ChatRoom chatRoom1 = chatRoomService.create("공부중1");
            //ChatRoom chatRoom2 = chatRoomService.create("study2");
            //ChatRoom chatRoom3 = chatRoomService.create("study3");
            IntStream.rangeClosed(1, 2).forEach(num -> {
                chatMessageService.create(chatRoom1, "김씨", "study back" + num);
            });
            System.out.println("This is not a production environment.");
        };
    }
}
