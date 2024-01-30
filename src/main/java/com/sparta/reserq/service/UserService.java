package com.sparta.reserq.service;

import com.sparta.reserq.domain.dto.PasswordUpdateDto;
import com.sparta.reserq.domain.dto.UserUpdateInfoDto;
import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long userId) {
        // 사용자 ID를 사용하여 데이터베이스에서 사용자 정보를 조회
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
    public void updateUserInfo(Long userId, UserUpdateInfoDto updateInfoDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        if (updateInfoDto.getName() != null) {
            existingUser.setName(updateInfoDto.getName());
        }

        if (updateInfoDto.getProfileImageUrl() != null) {
            existingUser.setProfileImageUrl(updateInfoDto.getProfileImageUrl());
        }

        if (updateInfoDto.getGreeting() != null) {
            existingUser.setGreeting(updateInfoDto.getGreeting());
        }

        userRepository.save(existingUser);
    }

    public void updatePassword(Long userId, PasswordUpdateDto passwordUpdateDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        if (passwordUpdateDto.getPassword() != null) {
            // 비밀번호 암호화
            String encryptedPassword = bCryptPasswordEncoder.encode(passwordUpdateDto.getPassword());
            existingUser.setPassword(encryptedPassword);
        }

        userRepository.save(existingUser);
    }
}