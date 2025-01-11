package com.li.chatBoat.domain.chatRoom.controller;

import com.li.chatBoat.domain.aiMessage.dto.AIMessageDto;
import com.li.chatBoat.domain.aiMessage.entity.AIMessage;
import com.li.chatBoat.domain.aiMessage.repository.AIMessageRepository;
import com.li.chatBoat.domain.aiMessage.service.AIMessageService;
import com.li.chatBoat.domain.chatMessage.dto.ChatMessageDto;
import com.li.chatBoat.domain.chatMessage.dto.RequestAddMessage;
import com.li.chatBoat.domain.chatMessage.entity.ChatMessage;
import com.li.chatBoat.domain.chatRoom.dto.ChatRoomDto;
import com.li.chatBoat.domain.chatRoom.dto.RequestCreateRoom;
import com.li.chatBoat.domain.chatRoom.entity.ChatRoom;
import com.li.chatBoat.domain.chatRoom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/chat/rooms")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // React 개발 서버 도메인
/*@CrossOrigin(
        origins = "https://cdpn.io"
)*/
public class ApiV1ChatRoomController {
    private final ChatRoomService chatRoomService;

    @GetMapping
    public List<ChatRoomDto> getChatRooms() {
        return chatRoomService.getAllRooms();
    }

    /*@GetMapping
    public List<ChatRoom> getChatRooms() {
        List<ChatRoom> ChatRooms = chatRoomService.getAll();
        return ChatRooms;
    }*/

    @GetMapping("/{roomId}")
    public ChatRoom getChatRoom(@PathVariable("roomId") Long roomId) {
        return chatRoomService.getChatRoom(roomId);
    }



    @PostMapping
    public ChatRoom createChatRoom(@RequestBody RequestCreateRoom requestCreateRoom) {
        ChatRoom chatRoom = chatRoomService.create(requestCreateRoom.getName());
        return chatRoom;
    }

    @PostMapping("/{roomId}/messages")
    public ChatMessage addMessage(
            @PathVariable Long roomId,
            @RequestBody RequestAddMessage request
    ) {
        System.out.println("받은 메시지 데이터: " + request);
        return chatRoomService.addMessageToRoom(roomId, request.getWriterName(), request.getContent());
    }


    @GetMapping("/{roomId}/messages")
    public List<ChatMessageDto> getMessages(@PathVariable Long roomId) {
        ChatRoom chatRoom = getChatRoom(roomId);
        return chatRoom.getChatMessages().stream()
                .map(message -> {
                    ChatMessageDto dto = new ChatMessageDto();
                    dto.setWriterName(message.getWriterName());
                    dto.setContent(message.getContent());
                    dto.setCreatedAt(message.getCreateDate().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private final OpenAiChatModel openAiChatModel; // OpenAiChatModel 필드 추가
    private final AIMessageService aiMessageService;


    @PostMapping("/ai")
    public Map<String, String> chatWithAi(@RequestBody RequestAddMessage request) {
        Map<String, String> responses = new HashMap<>();
        try {
            // OpenAI로 메시지를 전송하고 응답 받기
            String userMessage = request.getContent();
            String aiResponse = openAiChatModel.call(userMessage);

            // 사용자 메시지 저장
            aiMessageService.saveMessage("사용자", userMessage);

            // AI 응답 메시지 저장
            aiMessageService.saveMessage("AI", aiResponse);

            // 응답 반환
            responses.put("response", aiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            responses.put("error", "Failed to communicate with OpenAI: " + e.getMessage());
        }
        return responses;
    }

    @GetMapping("/ai/messages")
    public List<AIMessageDto> getAIMessages() {
        return aiMessageService.getAllMessages();
    }


    /*@PostMapping("/ai")
    public Map<String, String> chatWithAi(@RequestBody RequestAddMessage request) {
        Map<String, String> responses = new HashMap<>();
        try {
            // OpenAI로 메시지를 전송하고 응답 받기
            String aiResponse = openAiChatModel.call(request.getContent());
            responses.put("response", aiResponse);
        } catch (Exception e) {
            responses.put("error", "Failed to communicate with OpenAI: " + e.getMessage());
        }
        return responses;
    }*/

}

