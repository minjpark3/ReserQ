package com.example.mo_activity.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "newsfeedClient", url = "http://localhost:8082")
public interface NewsfeedClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/internal/newsfeeds", consumes = "application/json")
    void create(NewsfeedClientReq newsfeedClientReq);
}