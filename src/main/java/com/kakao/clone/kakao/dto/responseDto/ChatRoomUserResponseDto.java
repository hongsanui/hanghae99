package com.kakao.clone.kakao.dto.responseDto;

import com.kakao.clone.kakao.model.User;
import lombok.*;

@ToString
@Getter
@Setter
public class ChatRoomUserResponseDto {
    private Long id;
    private String username;
    private String nickname;
    private String profileImage;
    private String profileBgImage;

    public ChatRoomUserResponseDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.profileImage = user.getProfileImage();
        this.profileBgImage = user.getProfileBgImage();
    }
}
