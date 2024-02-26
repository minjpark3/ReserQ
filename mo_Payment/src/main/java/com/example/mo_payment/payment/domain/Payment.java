package com.example.mo_payment.payment.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity(name = "payments")
@Table(name = "payments")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Payment  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "orderId")
    private Long orderId;

    @Column(name = "totalPayment")
    private Long totalPayment;

    @CreationTimestamp
    @Column(name = "createDate")
    private LocalDateTime createDate;


}