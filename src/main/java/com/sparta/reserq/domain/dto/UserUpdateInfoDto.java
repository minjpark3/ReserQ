package com.sparta.reserq.domain.dto;

import lombok.Data;

@Data
public class UserUpdateInfoDto {

    private String name;
    private String profileImageUrl;
    private String greeting;
}