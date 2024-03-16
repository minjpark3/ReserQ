package com.example.mo_payment.order.dto;

import com.example.mo_payment.core.ProductType;
import com.example.mo_payment.order.domain.Order;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrderResponse {

    private ProductType productType;

    private Long productId;

    private Integer quantity;

    private Long paymentId;

    private Boolean status;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;


    public static OrderResponse from(Order order) {
        if (order == null) return null;

        return OrderResponse.builder()
                .productType(order.getProductType())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .paymentId(order.getPaymentId())
                .status(order.getStatus())
                .createDate(order.getCreateDate())
                .updateDate(order.getUpdateDate())
                .build();
    }
}
