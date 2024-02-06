package com.sparta.reserq.service;

import com.sparta.reserq.domain.likes.CommentLikesRepository;
import com.sparta.reserq.domain.likes.PostsLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class LikesService {
    private final PostsLikesRepository postsLikesRepository;
    private final CommentLikesRepository commentLikesRepository;
    // 포스트 좋아요
    @Transactional
    public void postLike(Long postsId,Long principalId){
        postsLikesRepository.mLikes(postsId,principalId);
    }
    @Transactional
    public void unPostLike(Long postsId,Long principalId){
        postsLikesRepository.mUnLikes(postsId,principalId);
    }

    // 댓글 좋아요
    @Transactional
    public void commentLike(Long commentId,Long principalId){
        commentLikesRepository.mCLikes(commentId,principalId);
    }
    @Transactional
    public void unCommentLike(Long commentId,Long principalId){
        commentLikesRepository.mCUnLikes(commentId,principalId);
    }
}
