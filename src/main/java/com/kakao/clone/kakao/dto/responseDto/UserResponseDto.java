package com.kakao.clone.kakao.dto.responseDto;

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
}
