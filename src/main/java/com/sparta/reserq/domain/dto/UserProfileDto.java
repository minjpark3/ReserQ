package com.sparta.reserq.domain.dto;

import com.sparta.reserq.domain.user.User;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserProfileDto {
    private boolean pageOwnerState;
    private boolean subscribeState;
    private User user;
}