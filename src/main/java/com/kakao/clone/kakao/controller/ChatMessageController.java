package com.kakao.clone.kakao.controller;

import com.kakao.clone.kakao.dto.requestDto.ChatMessageRequestDto;
import com.kakao.clone.kakao.model.ChatMessage;
import com.kakao.clone.kakao.model.User;
import com.kakao.clone.kakao.repository.UserRepository;
import com.kakao.clone.kakao.security.jwt.JwtDecoder;
import com.kakao.clone.kakao.service.ChatMessageService;
import com.kakao.clone.kakao.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;


@RequiredArgsConstructor
@RestController
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final UserService userService;
    private final JwtDecoder jwtDecoder;
    private final UserRepository userRepository;


    // 채팅 메시지를 @MessageMapping 형태로 받는다
    // 웹소켓으로 publish 된 메시지를 받는 곳이다
    @MessageMapping("/api/chat/message")
    public void message(@RequestBody ChatMessageRequestDto messageRequestDto, @Header("token") String token) {

        // 로그인 회원 정보를 들어온 메시지에 값 세팅
        String nickname = jwtDecoder.decodeNickname(token);
        Optional<User> user1 = userRepository.findByNickname(nickname);
        User user = user1.get();
        messageRequestDto.setNickname(user.getNickname());
        messageRequestDto.setSender(user.getUsername());

        // 메시지 생성 시간 삽입
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String dateResult = sdf.format(date);
        messageRequestDto.setCreatedAt(dateResult);

        // DTO 로 채팅 메시지 객체 생성
        ChatMessage chatMessage = new ChatMessage(messageRequestDto, userService);

        // 웹소켓 통신으로 채팅방 토픽 구독자들에게 메시지 보내기
        chatMessageService.sendChatMessage(chatMessage);

        // MySql DB에 채팅 메시지 저장
        chatMessageService.save(chatMessage);
    }
}

    /*

     클라이언트에서    /pub/chat/message 로 발행 요청하면 컨트롤러가 해당 메시지를 받아 처리함
     메시지가 발행되면 /sub/chat/room/{roomId} 로 메시지를 send 함
     클라이언트에서는 (/sub/chat/room/{roomId}) 이 주소를 구독하고있다가 메시지가 전달되면 화면에 출력!
     /sub/chat/room/{roomId} 는 채팅룸을 구분하는 값이므로 pub/sub 에서 Topic의 역할을 함

     구독자(subscriber , /sub) 구현은 웹 뷰에서 stomp 라이브러리를 이용하여 subscriber 주소를 바라보고있는 코드를 작성해야함.

    */


