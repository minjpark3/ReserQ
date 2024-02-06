package com.sparta.reserq.domain.comment;

import com.sparta.reserq.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.posts = :posts")
    void deleteByPosts(@Param("posts") Posts posts);
}
