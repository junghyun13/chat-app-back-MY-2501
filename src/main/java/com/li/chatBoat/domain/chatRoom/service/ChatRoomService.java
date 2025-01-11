package com.li.chatBoat.domain.chatRoom.service;

import com.li.chatBoat.domain.chatMessage.entity.ChatMessage;
import com.li.chatBoat.domain.chatMessage.repository.ChatMessageRepository;
import com.li.chatBoat.domain.chatMessage.service.ChatMessageService;
import com.li.chatBoat.domain.chatRoom.dto.ChatRoomDto;
import com.li.chatBoat.domain.chatRoom.entity.ChatRoom;
import com.li.chatBoat.domain.chatRoom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom create(String name) {
        ChatRoom chatRoom = ChatRoom.builder()
                .name(name)
                .build();

        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }

    /*public List<ChatRoom> getAll() {
        return chatRoomRepository.findAll();
    }*/

    public List<ChatRoomDto> getAllRooms() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        return chatRooms.stream()
                .map(room -> {
                    ChatRoomDto dto = new ChatRoomDto();
                    dto.setId(room.getId());
                    dto.setName(room.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public ChatRoom getChatRoom(Long roomId){
        return chatRoomRepository.findById(roomId).orElse(null);
    }

    private final ChatMessageRepository chatMessageRepository;
    @Transactional
    public ChatMessage addMessageToRoom(Long roomId, String writerName, String content) {
        ChatRoom chatRoom = getChatRoom(roomId);
        if (chatRoom == null) {
            throw new RuntimeException("채팅방을 찾을 수 없습니다.");
        }

        // 메시지 생성 및 ChatRoom에 추가
        ChatMessage chatMessage = ChatMessage.builder()
                .writerName(writerName)
                .content(content)
                .chatRoom(chatRoom)
                .build();

        chatRoom.getChatMessages().add(chatMessage); // ChatRoom의 메시지 리스트에 추가

        // ChatMessage를 명시적으로 저장
        chatMessageRepository.save(chatMessage);

        // ChatRoom 저장 (CascadeType.ALL이 제대로 설정되어 있다면 메시지도 저장)
        chatRoomRepository.save(chatRoom);

        return chatMessage;
    }


    /*@Transactional
    public ChatMessage addMessageToRoom(Long roomId, String writerName, String content) {
        ChatRoom chatRoom = getChatRoom(roomId);
        ChatMessage chatMessage = ChatMessage.builder()
                .writerName(writerName)
                .content(content)
                .chatRoom(chatRoom)
                .build();
        chatRoom.getChatMessages().add(chatMessage); // chatMessages 리스트에 추가 (필요하면서도 에러와 직접적인 관련은 없음)
        chatRoomRepository.save(chatRoom);  // ChatRoom 객체 저장
        return chatMessage;
    }*/

}
