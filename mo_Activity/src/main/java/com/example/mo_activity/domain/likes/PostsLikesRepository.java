package com.example.mo_activity.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsLikesRepository extends JpaRepository<PostsLikes, Long> {
//    @Modifying
//    @Query(value = "INSERT INTO PostsLikes(postsId, userId, createDate) VALUES(:postsId, :principalId,CURRENT_TIMESTAMP)", nativeQuery = true)
//    int mLikes(@Param("postsId")Long postsId,@Param("principalId")Long principalId);
//
    @Modifying
    @Query(value = "DELETE FROM PostsLikes WHERE postsId = :postsId AND userId = :principalId", nativeQuery = true)
    int mUnLikes(@Param("postsId")Long postsId,@Param("principalId")Long principalId);

//    boolean existsByPostIdAndUserId(Long postId, Long userId);
//
//    boolean existsByActivityIdAndUserId(Long activityId, Long userId);


}

