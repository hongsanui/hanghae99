package com.kakao.clone.kakao.repository;

import com.kakao.clone.kakao.dto.requestDto.FollowDto;
import com.kakao.clone.kakao.dto.responseDto.UserResponseDto;
import com.kakao.clone.kakao.model.Follow;
import com.kakao.clone.kakao.model.User;
import com.kakao.clone.kakao.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    // 팔로우 하기
    @Transactional
    public Follow save(Long userid, UserDetailsImpl userDetails) {
        // 현재 로그인된 유저 정보
        User fromUser = userDetails.getUser();
        // 팔로우 할 유저 정보
        User toUser = userRepository.findById(userid).get();

        return followRepository.save(new Follow(fromUser, toUser));
    }

    // 언팔로우
    @Transactional
    public void unfollow(Long userid, UserDetailsImpl userDetails) {
        // 현재 로그인된 유저 정보
        User fromUser = userDetails.getUser();
        // 언팔로우 할 유저 정보
        User toUser = userRepository.findById(userid).get();

        followRepository.deleteByFromUserAndToUser(fromUser,toUser);

    }

    // 해당 프로필을 따르는 사람들(팔로워) -> 프로필유저, 로그인된유저, 다른 사람들
    public List<FollowDto> getfollower(Long userid, UserDetailsImpl userDetails) {
        // 프로필 유저에 대한 유저정보
        User user = userRepository.findById(userid).orElseThrow(
                () -> new IllegalArgumentException("없는 유저입니다."));

        // 로그인 된 유저와 프로필 유저 일치 여부
        boolean isloginUser = userid.equals(userDetails.getUser().getId());

        List<Follow> followerList = followRepository.findAllByFromUser(user);
        List<FollowDto> followerDtoList = new ArrayList<>();

        for (Follow follower : followerList) {
            User followUser = follower.getFromUser();
            // 팔로워들의 정보
            UserResponseDto followUserDto = new UserResponseDto(followUser);
            // 로그인된 유저가 해당 팔로워들을 팔로우 하는지 여부
            boolean followState = followRepository.existsByFromUserAndToUser(userDetails.getUser(), followUser);

            FollowDto followDto = new FollowDto(followUserDto, followState, isloginUser);
            followerDtoList.add(followDto);

        }
        return followerDtoList;
    }

    // 해당 프로필이 따르는 사람들(팔로워)
    public List<FollowDto> getfollowing(Long userid, UserDetailsImpl userDetails) {
        // 로그인 된 유저와 프로필 유저 일치 여부
        boolean isloginUser = userid.equals(userDetails.getUser().getId());
        // 프로필 유저에 대한 유저정보
        User user = userRepository.findById(userid).orElseThrow(
                () -> new IllegalArgumentException("없는 유저입니다."));

        List<Follow> followingList = followRepository.findAllByToUser(user);
        List<FollowDto> followingDtoList = new ArrayList<>();

        for (Follow following : followingList) {
            User followUser = following.getToUser();
            // 팔로워들의 정보
            UserResponseDto followUserDto = new UserResponseDto(followUser);
            // 로그인된 유저가 해당 팔로잉들을 팔로우 하는지 여부
            boolean followState = followRepository.existsByFromUserAndToUser(userDetails.getUser(), followUser);

            FollowDto followDto = new FollowDto(followUserDto, followState, isloginUser);
            followingDtoList.add(followDto);

        }
        return followingDtoList;
    }
}