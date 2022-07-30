package com.kakao.clone.kakao.dto.responseDto;

import com.kakao.clone.kakao.model.ChatRoom;
import com.kakao.clone.kakao.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ChatRoomListDto {

    private Long id;

    private String roomName;

    private List<User> userList = new ArrayList<>();

    private String nickname;

    private String roomCreator;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public ChatRoomListDto(ChatRoom chatRoom, User user){
        this.id = chatRoom.getId();
        this.roomName = chatRoom.getRoomName();
        this.userList = chatRoom.getUserList();
        this.nickname = user.getNickname();
        this.roomCreator = chatRoom.getRoomCreator();
        this.createdAt = chatRoom.getCreatedAt();
        this.modifiedAt = chatRoom.getModifiedAt();
    }
}
