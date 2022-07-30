package com.kakao.clone.kakao.dto.responseDto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String nickname;
    private String profileImage;
    private String profileBgImage;
    private String Letter;
    private String username;

    public LoginResponseDto(String nickname, String profileImage, String profileBgImage, String letter, String username) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.profileBgImage = profileBgImage;
        Letter = letter;
        this.username = username;
    }
}
