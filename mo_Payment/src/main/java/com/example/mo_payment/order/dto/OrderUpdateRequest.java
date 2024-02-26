package com.example.mo_payment.order.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderUpdateRequest {

    private Long paymentId;
    private Boolean status;
}
