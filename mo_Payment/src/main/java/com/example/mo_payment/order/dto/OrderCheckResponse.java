package com.example.mo_payment.order.dto;

import com.example.mo_payment.core.ProductType;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderCheckResponse {

    private ProductType productType;
    private Long productId;
    private Integer quantity;
    private Long orderId;
    private Boolean status;


    public OrderCheckResponse(Long productId, Boolean status) {
        this.productId = productId;
        this.status = status;
    }

    public OrderCheckResponse(ProductType productType, Long productId, Integer quantity) {
        this.productType = productType;
        this.productId = productId;
        this.quantity = quantity;
        this.status = false; // 이 부분은 상황에 따라 다를 수 있습니다. 이 예제에서는 status를 null로 설정합니다.
    }
}