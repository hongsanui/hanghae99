package com.kakao.clone.kakao.controller;

import com.kakao.clone.kakao.dto.requestDto.FollowDto;
import com.kakao.clone.kakao.model.Follow;
import com.kakao.clone.kakao.repository.FollowService;
import com.kakao.clone.kakao.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FollowController {

    private final FollowService followService;

    // 팔로우
    @PostMapping("/follow/{userid}")
    public Follow followUser(@PathVariable Long userid, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return followService.save(userid, userDetails);
    }

    // 언팔로우
    @DeleteMapping("/follow/{userid}")
    public String unfollowUser(@PathVariable Long userid, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        followService.unfollow(userid, userDetails);
        return "삭제완료";
    }

    // 팔로워 조회
    @GetMapping("/follow/{userid}/follower")
    public List<FollowDto> getFollower(@PathVariable Long userid, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return followService.getfollower(userid, userDetails);
    }

    // 팔로잉 조회
    @GetMapping("/follow/{userid}/following")
    public List<FollowDto> getFollowing(@PathVariable Long userid, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return followService.getfollowing(userid, userDetails);
    }
}
