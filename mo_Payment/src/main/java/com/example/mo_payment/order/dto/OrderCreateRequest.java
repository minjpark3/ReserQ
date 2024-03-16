package com.example.mo_payment.order.dto;

import com.example.mo_payment.core.ProductType;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderCreateRequest {
    private Long userId;
    private Long productId;
    private ProductType productType;
    private Integer quantity;

}