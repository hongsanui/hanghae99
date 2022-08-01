package com.kakao.clone.kakao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long myid;

    // 다른 사람이 나를 팔로우(팔로워)
    @JoinColumn(name = "from_user_id")
    @ManyToOne
    @JsonIgnore
    private User fromUser;

    // 내가 다른 사람을 팔로우(팔로잉)
    @JoinColumn(name = "to_user_id")
    @ManyToOne
    @JsonIgnore
    private User toUser;

    public Follow(User fromUser, User toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
    }
}
