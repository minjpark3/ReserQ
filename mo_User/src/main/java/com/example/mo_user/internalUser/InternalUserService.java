package com.example.mo_user.internalUser;


import com.example.mo_user.domain.user.User;
import com.example.mo_user.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class InternalUserService {
    private final UserRepository userRepository;


    public InternalUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkUserExists(Long userId) {
        return userRepository.existsById(userId);
    }

    public String findUserName(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException ("사용자를 찾을 수 없습니다."));

        return findUser.getName();
    }
}