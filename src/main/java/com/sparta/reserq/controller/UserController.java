package com.sparta.reserq.controller;

import com.sparta.reserq.domain.dto.PasswordUpdateDto;
import com.sparta.reserq.domain.dto.UserUpdateInfoDto;
import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.service.UserService;
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

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
        // 사용자 ID를 받아와서 상세 정보 조회
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{userId}/update-info")
    public ResponseEntity<String> updateUserInfo(
            @PathVariable Long userId,
            @RequestBody UserUpdateInfoDto updateInfoDto) {
        try {
            userService.updateUserInfo(userId, updateInfoDto);
            return ResponseEntity.ok("User information updated successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @PutMapping("/{userId}/update-password")
    public ResponseEntity<String> updatePassword(
            @PathVariable Long userId,
            @RequestBody PasswordUpdateDto passwordUpdateDto) {
        try {
            userService.updatePassword(userId, passwordUpdateDto);
            return ResponseEntity.ok("Password updated successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }
    }

