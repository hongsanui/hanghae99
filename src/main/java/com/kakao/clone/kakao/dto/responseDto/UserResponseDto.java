package com.kakao.clone.kakao.dto.responseDto;

import com.kakao.clone.kakao.model.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String username;
    private String nickname;
    private String profileImage;
    private String profileBgImage;
    private String message;

    public UserResponseDto(String username, String nickname, String profileImage, String profileBgImage, String message) {
        this.username = username;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.profileBgImage = profileBgImage;
        this.message = message;
    }

    public UserResponseDto(User user){
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.profileImage = user.getProfileImage();
        this.profileBgImage = user.getProfileBgImage();
    }
}
