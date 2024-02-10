package com.example.mo_activity.service;

import com.example.mo_activity.domain.likes.PostsLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class LikesService {
    private final PostsLikesRepository postsLikesRepository;

    @Transactional
    public void postLike(Long postsId, Long principalId) {
        if (principalId == null) {
            throw new IllegalArgumentException("사용자 ID가 유효하지 않습니다.");
        }
        postsLikesRepository.mLikes(postsId, principalId);
    }

    @Transactional
    public void unPostLike(Long postsId, Long principalId) {
        if (principalId == null) {
            throw new IllegalArgumentException("사용자 ID가 유효하지 않습니다.");
        }
        postsLikesRepository.mUnLikes(postsId, principalId);
    }
}