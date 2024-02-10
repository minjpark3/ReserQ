package com.example.mo_user.domain.dto;

import com.example.mo_user.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserProfileDto {
    private boolean pageOwnerState;
    private boolean subscribeState;
    private User user;
}