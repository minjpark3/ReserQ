package com.sparta.reserq.service;

import com.sparta.reserq.domain.newsFeed.NewsFeedRepository;
import com.sparta.reserq.domain.posts.Posts;
import com.sparta.reserq.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsFeedService {
    private final NewsFeedRepository newsFeedRepository;
    private final UserService userService;
    private final PostsService postsService;


    public List<Posts> getFollowedPosts(Long userId) {
        // 팔로우한 사용자 목록 가져오기
        List<User> followedUsers = userService.getFollowedUsers(userId);

        // 각 사용자의 Posts를 가져와서 하나의 목록으로 합치기
        List<Posts> followedPosts = new ArrayList<>();
        for (User followedUser : followedUsers) {
            List<Posts> userPosts = postsService.getPostsByUserId(followedUser.getId());
            followedPosts.addAll(userPosts);
        }

        // 팔로우한 사용자들의 Posts를 반환
        return followedPosts;
    }




}
