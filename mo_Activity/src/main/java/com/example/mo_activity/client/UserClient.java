package com.example.mo_activity.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "userClient", url = "http://localhost:8080")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/internal/users/check", consumes = "application/json")
    boolean checkUserExists(@RequestParam(name = "userId") Long userId);
}