package com.kakao.clone.kakao.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    private String username;
    private String nickname;
    private String password;
    private String passwordCheck;
    private String profileImage;
    private String profileBgImage;
    private String userStatus;
}
