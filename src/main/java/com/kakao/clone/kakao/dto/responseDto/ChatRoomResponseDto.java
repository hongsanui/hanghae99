package com.kakao.clone.kakao.dto.responseDto;

import com.kakao.clone.kakao.model.ChatRoom;
import com.kakao.clone.kakao.model.User;
import lombok.*;

@Getter
@Setter
@ToString
public class ChatRoomResponseDto {

    private Long id;
    private String roomName;
    private String nickname;

    public ChatRoomResponseDto(ChatRoom chatRoom, User user) {
        this.id = chatRoom.getId();
        this.roomName = chatRoom.getRoomName();
        this.nickname = user.getNickname();
    }
}
