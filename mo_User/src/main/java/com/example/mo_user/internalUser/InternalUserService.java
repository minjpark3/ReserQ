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

    public boolean checkUserExists(Long principalId) {
        return userRepository.existsById(principalId);
    }

    public String findUserName(Long principalId) {
        User findUser = userRepository.findById(principalId)
                .orElseThrow(()->new IllegalArgumentException ("can't find user"));

        return findUser.getName();
    }
}