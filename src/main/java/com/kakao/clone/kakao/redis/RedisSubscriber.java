package com.kakao.clone.kakao.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.clone.kakao.model.ChatMessage;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    /**
     * Redis에서 메시지가 발행(publish)되면 대기하고 있던 Redis Subscriber가 해당 메시지를 받아 처리한다.
     * 수신된 메시지는 /sub/chat/room/{roomId}를 구독한 웹소켓 클라이언트에게 메시지 발송된다.
     */
    public void sendMessage(String publishMessage) {
        log.info("데이터 잘 왔는지 체크! publishMessage={}",publishMessage);
        try {
            // ChatMessage 객채로 맵핑
            ChatMessage chatMessage = objectMapper.readValue(publishMessage, ChatMessage.class);
            // 채팅방을 구독한 클라이언트에게 메시지 발송
            messagingTemplate.convertAndSend("/sub/chat/room/" + chatMessage.getRoomId(), chatMessage);
        } catch (Exception e) {
            log.error("Exception {}", e);
        }
    }
}
