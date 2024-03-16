package com.example.mo_user.controller;

import com.example.mo_user.domain.dto.PasswordUpdateDto;
import com.example.mo_user.domain.dto.UserUpdateDto;
import com.example.mo_user.domain.user.User;
import com.example.mo_user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long userId,
            @RequestBody User user) {
        try {
            UserUpdateDto userUpdateDto = new UserUpdateDto(user.getName(), user.getProfileImageUrl(), user.getGreeting());

            User updatedUser = userService.updateUserInfo(userId, userUpdateDto);
            return ResponseEntity.ok(updatedUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PutMapping("/update/password/{userId}")
    public ResponseEntity<String> updatePassword(
            @PathVariable Long userId,
            @RequestBody PasswordUpdateDto passwordUpdateDto) {
        try {
            userService.updatePassword(userId, passwordUpdateDto);
            return ResponseEntity.ok("비밀번호가 변경되었습니다..");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    }

