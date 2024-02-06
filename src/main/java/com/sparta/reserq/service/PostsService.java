package com.sparta.reserq.service;


import com.sparta.reserq.domain.comment.CommentRepository;
import com.sparta.reserq.domain.dto.PostsReqDto;
import com.sparta.reserq.domain.posts.Posts;
import com.sparta.reserq.domain.posts.PostsRepository;
import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.domain.user.UserRepository;
import com.sparta.reserq.config.jwt.UserDetailsImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    @Transactional
    public Posts savePost(String title, String content, Long userId) {
        User user = userService.getUserById(userId);
        Posts posts = Posts.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
        return postsRepository.save(posts);
    }

//    @Transactional(readOnly = true)
//    public List<Posts>인기사진(){
//        return postsRepository.mPopular();
//    }
//
//    @Transactional(readOnly = true)//영속성 컨텍스트변경 감지해서 ,더티체킹, flush반영
//    public Page<Posts> 이미지스토리(int principalId, Pageable pageable){
//        Page<Posts>posts = postsRepository.mStroy(principalId, pageable);
//
//        //images에 좋아요 상태 담기
//        posts.forEach((image) -> {
//
//            image.setLikeCount(image.getLikes().size());
//            image.getLikes().forEach(likes -> {
//                if(likes.getUser().getId()==principalId){//해당이미지에 좋아요한 사람들을 찾아서 현재로그인한 사람이 좋아요 한것인지 비교
//                    image.setLikeState(true);
//                }
//            });
//        });
//        return posts;
//    }


    //===========================================================
    //===========================================================

    public List<Posts> getAllPosts() {
        return postsRepository.findAll();
    }

    public List<Posts> getPostsByUserId(Long userId) {
        return postsRepository.findByUserId(userId);
    }

//    public void createPost(PostsReqDto postRequest, UserDetailsImpl principalDetails) {
//        Posts posts = postRequest.toEntity(principalDetails.getEmail());
//        Posts postsEntity = postsRepository.save(posts);
//    }


    public Posts updatePost(Long id, PostsReqDto postsReqDto) {
        Optional<Posts> existingPostOptional = postsRepository.findById(id);

        if (existingPostOptional.isPresent()) {
            Posts existingPost = existingPostOptional.get();
            existingPost.setTitle(postsReqDto.getTitle());
            existingPost.setContent(postsReqDto.getContent());

            // 업데이트된 게시글 정보를 저장
            return postsRepository.save(existingPost);
        } else {
            throw new EntityNotFoundException("게시글을 찾을 수 없음: " + id);
        }
    }

    @Transactional
    public void deletePost(Long postId, UserDetailsImpl userDetails) {
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("포스트를 찾을 수 없음: " + postId));

        if (!post.getUser().getId().equals(userDetails.getId())) {
            throw new AccessDeniedException("포스트를 삭제할 권한이 없습니다.");
        }

        commentRepository.deleteByPosts(post);
        postsRepository.delete(post);
    }
}

