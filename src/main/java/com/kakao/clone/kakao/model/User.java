package com.kakao.clone.kakao.model;

import com.kakao.clone.kakao.dto.requestDto.UserRequestDto;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    //회원번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //회원 아이디
    @Column(nullable = false, unique = true)
    private String username;

    //회원 닉네임
    @Column(nullable = false)
    private String nickname;

    //비밀번호
    @Column(nullable = false)
    private String password;

    //프로필 이미지 Url
    @Column(nullable = false)
    private String profileImage;

    //프로필 배경이미지 Url
    @Column(nullable = false)
    private String profileBgImage;

    //회원 상태명
    @Column(nullable = false)
    private String userStatus;

    public User(String username, String nickname, String password, String profileImage, String profileBgImage, String userStatus) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.profileImage = profileImage;
        this.profileBgImage = profileBgImage;
        this.userStatus = userStatus;
    }

    public User(String nickname, String profileImage, String profileBgImage){
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.profileBgImage = profileBgImage;
    }

    public User(UserRequestDto userRequestDto){
        this.username = userRequestDto.getUsername();
        this.nickname = userRequestDto.getNickname();
        this.password = userRequestDto.getPassword();
        this.profileImage = userRequestDto.getProfileImage();
        this.profileBgImage = userRequestDto.getProfileBgImage();
    }

    public User(String username, String nickname, String profileImage, String profileBgImage){
        this.username = username;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.profileBgImage = profileBgImage;
    }
}
