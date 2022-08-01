package com.kakao.clone.kakao.dto.requestDto;

import com.kakao.clone.kakao.dto.responseDto.UserResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowDto {
    private String nickname;
    private String profile;
    private boolean followState;
    private boolean isLoginUser;

    public FollowDto(UserResponseDto followUserDto, boolean followState, boolean isLoginUser) {
        this.nickname = followUserDto.getNickname();
        this.profile = followUserDto.getProfileImage();
        this.followState = followState;
        this.isLoginUser = isLoginUser;
    }
}
