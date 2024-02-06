package com.sparta.reserq.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
    @Modifying
    @Query(value = "INSERT INTO CommentLikes(commentId, userId, createDate) VALUES(:commentId, :principalId,CURRENT_TIMESTAMP)", nativeQuery = true)
    int mCLikes(@Param("commentId")Long commentId, @Param("principalId")Long principalId);

    @Modifying
    @Query(value = "DELETE FROM CommentLikes WHERE commentId = :commentId AND userId = :principalId", nativeQuery = true)
    int mCUnLikes(@Param("commentId")Long commentId,@Param("principalId")Long principalId);

}