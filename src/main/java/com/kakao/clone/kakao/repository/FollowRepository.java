package com.kakao.clone.kakao.repository;

import com.kakao.clone.kakao.model.Follow;
import com.kakao.clone.kakao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {

    List<Follow> findAllByFromUser(User fromUser);
    List<Follow> findAllByToUser(User toUser);

    Long countAllByFromUser(User FromUser);
    Long countAllByToUser(User ToUser);

    void deleteByFromUserAndToUser(User fromUser, User toUser);

    boolean existsByFromUserAndToUser(User fromUser, User toUser);
}
