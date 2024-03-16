package com.example.mo_payment.payment.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EnterPaymentRequest {

    @NotNull
    @Min(1)
    private Integer quantity;

}