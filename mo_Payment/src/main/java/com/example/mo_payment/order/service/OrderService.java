package com.example.mo_payment.order.service;

import com.example.mo_payment.order.domain.Order;
import com.example.mo_payment.order.domain.OrderRepository;
import com.example.mo_payment.order.dto.OrderCheckResponse;
import com.example.mo_payment.order.dto.OrderCreateRequest;
import com.example.mo_payment.order.dto.OrderUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // 주문 상세 조회
    public Order getOrderDetails(Long userId, Long orderId) {
        return orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    // 주문 목록 조회
    public List<Order> getOrderList(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    //======================================================
    // 결제 진입 주문서 발행
    @Transactional
    public void createOrder(OrderCreateRequest orderCreateRequest) {
        Order order = Order.builder()
                .userId(orderCreateRequest.getUserId())
                .productType(orderCreateRequest.getProductType())
                .productId(orderCreateRequest.getProductId())
                .quantity(orderCreateRequest.getQuantity())
                .status(false)
                .build();

        orderRepository.save(order);
    }

    // 주문서 업데이트
    @Transactional
    public void updateOrder(Long orderId, OrderUpdateRequest orderUpdateRequest) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found."));

        order.updateOrder(orderUpdateRequest);

        orderRepository.save(order);
    }
    @Transactional(readOnly = true)
    public OrderCheckResponse checkOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문서가 존재하지 않습니다."));

        if (order.getStatus()) {
            throw new RuntimeException("이미 결제 완료된 주문서입니다.");
        }

        return new OrderCheckResponse(order.getProductType(), order.getProductId(), order.getQuantity());
    }

//    // 주문서 확인
//    @Transactional(readOnly = true)
//    public Order checkOrder(Long orderId) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new RuntimeException("주문서가 존재하지 않습니다."));
//
//        if (order.getStatus()) {
//            throw new RuntimeException("이미 결제 완료된 주문서입니다,");
//        }
//
//        return order;
//    }
//    @Transactional(readOnly = true)
//    public Order chekOrder(Long orderId, Long userId) {
//        Order order = orderRepository.findByIdAndUserId(orderId, userId)
//                .orElseThrow(() -> new RuntimeException("주문서가 존재하지 않습니다."));
//
//        if (order.getStatus()) {
//            throw new RuntimeException("이미 결제 완료된 주문서입니다,");
//        }
//
//        return order;
//    }

    // 주문서 삭제
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    // 주문 확인
//    public OrderCheckResponse checkOrder(Long orderId) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
//
//        return new OrderCheckResponse(order.getId(), order.getStatus());
//    }



}