package com.sparta.reserq.domain.dto;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CMRespDto<T> {
    private int code;// 1은 성공 -1은 실패
    private String message;
    private T data;
}
