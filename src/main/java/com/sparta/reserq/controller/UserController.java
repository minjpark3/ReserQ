package com.sparta.reserq.controller;

import com.sparta.reserq.config.jwt.UserDetailsImpl;
import com.sparta.reserq.domain.dto.PasswordUpdateDto;
import com.sparta.reserq.domain.dto.UserProfileDto;
import com.sparta.reserq.domain.dto.UserUpdateDto;
import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{userId}")
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
    @PutMapping("/{userId}/update-password")
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


    //===========================================================
    //===========================================================
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
    }

