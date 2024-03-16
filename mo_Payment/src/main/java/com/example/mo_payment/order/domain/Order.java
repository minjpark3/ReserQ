package com.example.mo_payment.order.domain;

import com.example.mo_payment.core.ProductType;
import com.example.mo_payment.order.dto.OrderUpdateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity(name = "orders")
@Table(name = "orders")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId", nullable = false, updatable = false)
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "productType", nullable = false)
    private ProductType productType;

    @Column(name = "productId", nullable = false)
    private Long productId;

    @Column(name = "paymentId")
    private Long paymentId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "isOrdered", nullable = false)
    private Boolean status;

    @CreationTimestamp
    @Column(name = "createDate")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "updateDate")
    private LocalDateTime updateDate;

    public void updateOrder(OrderUpdateRequest orderUpdateRequest) {
        if(orderUpdateRequest.getPaymentId() != null) this.paymentId = orderUpdateRequest.getPaymentId();
        if(orderUpdateRequest.getStatus() != null) this.status = orderUpdateRequest.getStatus();
    }
}