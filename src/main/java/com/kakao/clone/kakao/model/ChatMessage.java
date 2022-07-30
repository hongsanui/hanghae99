package com.kakao.clone.kakao.model;

import com.kakao.clone.kakao.dto.requestDto.ChatMessageRequestDto;
import com.kakao.clone.kakao.service.UserService;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatMessage {
    // 메시지 타입 : 입장, 퇴장, 채팅
    public enum MessageType {
        ENTER, QUIT, TALK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private MessageType type; // 메시지 타입

    @Column
    private String roomId; // 방번호

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_joined")
    private User user;

    // Redis MessageListener 로 뒙소켓을 통해 바로 채팅방에 메시지를 전달해주기 위한 값을 따로 설정해주었다
    @Column
    private String nickname;

    @Column
    private String sender; // 메시지 보낸사람

    @Column
    private String message; // 메시지

    @Column
    private String createdAt;   //발송시간

    @Builder
    public ChatMessage(MessageType type, String roomId, String nickname, String sender, String message, String createdAt) {
        this.type = type;
        this.roomId = roomId;
        this.user = null;
        this.nickname = nickname;
        this.sender = sender;
        this.message = message;
        this.createdAt = createdAt;
    }

    @Builder
    public ChatMessage(ChatMessageRequestDto chatMessageRequestDto) {
        this.type = chatMessageRequestDto.getType();
        this.roomId = chatMessageRequestDto.getRoomId();
        this.user = null;
        this.sender = chatMessageRequestDto.getSender();
        this.message = chatMessageRequestDto.getMessage();
        this.createdAt = chatMessageRequestDto.getCreatedAt();
    }

    @Builder
    public ChatMessage(ChatMessageRequestDto chatMessageRequestDto, UserService userService) {
        this.type = chatMessageRequestDto.getType();
        this.roomId = chatMessageRequestDto.getRoomId();
        this.user = userService.findByNickname(chatMessageRequestDto.getNickname());
        this.nickname = chatMessageRequestDto.getNickname();
        this.sender = chatMessageRequestDto.getSender();
        this.message = chatMessageRequestDto.getMessage();
        this.createdAt = chatMessageRequestDto.getCreatedAt();
    }

}
