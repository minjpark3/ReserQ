package com.sparta.reserq.service;

import com.sparta.reserq.domain.dto.PasswordUpdateDto;
import com.sparta.reserq.domain.dto.UserProfileDto;
import com.sparta.reserq.domain.follower.Follower;
import com.sparta.reserq.domain.follower.FollowerRepository;
import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        // 사용자 ID를 사용하여 데이터베이스에서 사용자 정보를 조회
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 못찾음"));
    }

    public User updateUserInfo(Long userId, User user) {
        User userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자 못찾음: " + userId));
        userEntity.setName(user.getName());
        userEntity.setProfileImageUrl(user.getProfileImageUrl());
        userEntity.setGreeting(user.getGreeting());
        userRepository.save(userEntity);

        return userEntity;

    }

    public void updatePassword(Long userId, PasswordUpdateDto passwordUpdateDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자 못찾음: " + userId));

        if (passwordUpdateDto.getPassword() != null) {
            // 비밀번호 암호화
            String encryptedPassword = bCryptPasswordEncoder.encode(passwordUpdateDto.getPassword());
            existingUser.setPassword(encryptedPassword);
        }

        userRepository.save(existingUser);
    }
    public List<User> getFollowedUsers(Long userId) {
        List<Follower> followers = followerRepository.findByFromUserId(userId);

        List<User> followedUsers = new ArrayList<>();
        for (Follower follower : followers) {
            followedUsers.add(follower.getToUser());
        }

        return followedUsers;
    }

    //============================================================
    //============================================================

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자 못찾음"));

    }
}