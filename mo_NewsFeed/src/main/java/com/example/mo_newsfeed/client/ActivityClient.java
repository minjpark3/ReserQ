package com.example.mo_newsfeed.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "activityClient", url = "http://localhost:8081")
public interface ActivityClient {

    /**
     * activity_service에 팔로우한 모든 사용자 id 요청
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/internal/follows", consumes = "application/json")
    List<Long> findFollowingIds(@RequestParam(name = "userId") Long userId);

//    @RequestMapping(method = RequestMethod.GET, value = "/api/posts/user/{userId}", consumes = "application/json")
//    List<PostsDto> getPostsByUserId(@PathVariable("userId") Long userId);

}