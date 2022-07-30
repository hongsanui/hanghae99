package com.kakao.clone.kakao.controller;

import com.kakao.clone.kakao.dto.requestDto.ChatRoomRequestDto;
import com.kakao.clone.kakao.dto.responseDto.ChatRoomListDto;
import com.kakao.clone.kakao.dto.responseDto.ChatRoomResponseDto;
import com.kakao.clone.kakao.dto.responseDto.InvitationDto;
import com.kakao.clone.kakao.model.ChatMessage;
import com.kakao.clone.kakao.security.UserDetailsImpl;
import com.kakao.clone.kakao.service.ChatMessageService;
import com.kakao.clone.kakao.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;


    // 채팅방 생성
    @PostMapping("/rooms")
    public ChatRoomResponseDto ChatRoomResponseDto(@RequestBody ChatRoomRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        requestDto.setNickname(userDetails.getUser().getNickname());
        ChatRoomResponseDto chatRoom = chatRoomService.createChatRoom(requestDto);
        return chatRoom;
    }

    // 전체 채팅방 목록 조회
    @GetMapping("/rooms")
    public List<ChatRoomListDto> getAllChatRooms(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return chatRoomService.getAllChatRooms(userDetails);
    }

    //유저 초대하기
    @PostMapping("/invite")
    public ResponseEntity<?> inviteUser(@RequestBody InvitationDto invitationDto){
        return chatRoomService.inviteUser(invitationDto);
    }

    //채팅방 나가기
    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity<?> outChatRoom(@PathVariable Long roomId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return chatRoomService.outChatRoom(roomId, userDetails.getUser());
    }

    // 채팅방 상세 조회
    @GetMapping("/rooms/{roomId}")
    public ChatRoomResponseDto getEachChatRoom(@PathVariable Long roomId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return chatRoomService.getEachChatRoom(roomId,userDetails.getUser());
    }

    // 채팅방 내 메시지 전체 조회
    @GetMapping("/rooms/{roomId}/messages")
    public Page<ChatMessage> getEachChatRoomMessages(@PathVariable String roomId, @PageableDefault Pageable pageable) {
        return chatMessageService.getChatMessageByRoomId(roomId, pageable);
    }
}