package com.sparta.reserq.domain.follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Long> {
    @Modifying
    @Query(value = "DELETE FROM follower WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
    void mUnSubscribe(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);

    @Modifying
    @Query(value = "INSERT INTO follower (fromUserId, toUserId, createDate) VALUES (:fromUserId, :toUserId, CURRENT_TIMESTAMP)", nativeQuery = true)
    void mSubscribe(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);

    List<Follower> findByFromUserId(@Param("fromUserId")Long fromUserId);
}