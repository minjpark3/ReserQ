package com.example.mo_user.service;

import com.example.mo_user.config.jwt.JwtTokenProvider;
import com.example.mo_user.config.jwt.TokenType;
import com.example.mo_user.domain.dto.LoginRequest;
import com.example.mo_user.domain.dto.PasswordUpdateDto;
import com.example.mo_user.domain.dto.UserUpdateDto;
import com.example.mo_user.domain.user.User;
import com.example.mo_user.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));
    }
    @Transactional
    public User updateUserInfo(Long userId, UserUpdateDto userUpdateDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다: " + userId));

        User updatedUser = userUpdateDto.toEntity();
        existingUser.update(updatedUser.getName(), updatedUser.getProfileImageUrl(), updatedUser.getGreeting());

        return userRepository.save(existingUser);
    }
    @Transactional
    public void updatePassword(Long userId, PasswordUpdateDto passwordUpdateDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다: " + userId));

        if (passwordUpdateDto.getPassword() != null) {
            String encryptedPassword = bCryptPasswordEncoder.encode(passwordUpdateDto.getPassword());
            existingUser.setPassword(encryptedPassword);
        }

        userRepository.save(existingUser);
    }
//    @Transactional
//    public List<User> getFollowedUsers(Long userId) {
//        List<Follower> followers = followerRepository.findByFromUserId(userId);
//
//        List<User> followedUsers = new ArrayList<>();
//        for (Follower follower : followers) {
//            followedUsers.add(follower.getToUser());
//        }
//
//        return followedUsers;
//    }
    @Transactional
    public void signUpAndSendEmail(User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        String verificationToken = generateVerificationToken();
        user.setVerificationToken(verificationToken);

        userRepository.save(user);
        emailService.sendVerificationEmail(user.getEmail(), verificationToken);
    }

    private String generateVerificationToken() {
        int randomNumber = (int) ((Math.random() * (999999 - 100000 + 1)) + 100000);
        return String.valueOf(randomNumber);
    }

    @Transactional
    public boolean verifyVerificationToken(String email, String verificationToken) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getVerificationToken().equals(verificationToken)) {
                user.setVerified(true);
                userRepository.save(user);
                return true;
            } else {
                userRepository.delete(user);
                // 실패한 경우 사용자 삭제 또는 다른 작업 수행
            }
        }
        return false;
    }

    public String login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                String token = jwtTokenProvider.generate(email, user.getName(), TokenType.ACCESS);
                jwtTokenProvider.getEmail(token, TokenType.ACCESS);
                return token;
            } else {
                throw new IllegalArgumentException("잘못된 비밀번호");
            }
        } else {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다");
        }
    }

    //============================================================
    //============================================================

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자 못찾음"));

    }
}