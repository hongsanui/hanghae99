package com.kakao.clone.kakao.model;

import com.kakao.clone.kakao.dto.requestDto.ChatRoomRequestDto;
import com.kakao.clone.kakao.service.UserService;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ChatRoom extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    @Column
    private String roomName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<User> userList = new ArrayList<>();

    private String roomCreator;


    public ChatRoom(ChatRoomRequestDto requestDto, UserService userService) {
        this.roomName = requestDto.getRoomName();
        this.userList.add(userService.findByNickname(requestDto.getNickname()));
        this.roomCreator = requestDto.getNickname();
    }

    public ChatRoom(String name) {
        this.roomName = name;
    }
}