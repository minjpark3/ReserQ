package com.sparta.reserq.service;

import com.sparta.reserq.CustomValidationApiException;
import com.sparta.reserq.config.jwt.UserDetailsImpl;
import com.sparta.reserq.domain.comment.Comment;
import com.sparta.reserq.domain.comment.CommentRepository;
import com.sparta.reserq.domain.posts.Posts;
import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment createComment(String content, Long postsId, Long userId){

        Posts posts= new Posts();
        posts.setId(postsId);
        User userEntity =userRepository.findById(userId).orElseThrow(()->{
            throw new CustomValidationApiException("유저 아이디를 찾을수 없습니다.");
        });

        Comment comment= new Comment();
        comment.setContent(content);
        comment.setPosts(posts);
        comment.setUser(userEntity);

        return commentRepository.save(comment);

    }
    @Transactional
    public void unComment(Long id, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없음: " + id));

        // 현재 로그인한 사용자의 ID와 댓글 작성자의 ID를 비교하여 확인
        if (!comment.getUser().getId().equals(userDetails.getId())) {
            throw new AccessDeniedException("댓글을 삭제할 권한이 없습니다.");
        }

        try {
            commentRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomValidationApiException(e.getMessage());
        }
    }
}