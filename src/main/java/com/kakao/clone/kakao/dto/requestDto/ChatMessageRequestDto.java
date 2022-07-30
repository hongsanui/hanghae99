package com.kakao.clone.kakao.dto.requestDto;

import com.kakao.clone.kakao.model.ChatMessage;
import lombok.*;

@Getter @Setter
public class ChatMessageRequestDto {

    private ChatMessage.MessageType type;
    private String roomId;
    private String nickname;
    private String sender;
    private String message;
    private String createdAt;
}
