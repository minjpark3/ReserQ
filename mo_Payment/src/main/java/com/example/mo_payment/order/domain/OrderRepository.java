package com.example.mo_payment.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndUserId(Long orderId, Long userId);
    Optional<Order> findById(Long orderId);
    List<Order> findByUserId(Long userId);

}