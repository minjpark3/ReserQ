package com.example.mo_payment.order.controller;

import com.example.mo_payment.order.domain.Order;
import com.example.mo_payment.order.dto.OrderCheckResponse;
import com.example.mo_payment.order.dto.OrderCreateRequest;
import com.example.mo_payment.order.dto.OrderUpdateRequest;
import com.example.mo_payment.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 상세 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long userId, @PathVariable Long orderId) {
        Order order = orderService.getOrderDetails(userId, orderId);
        return ResponseEntity.ok(order);
    }

    // 주문 목록 조회
    @GetMapping
    public ResponseEntity<List<Order>> getOrderList(@PathVariable Long userId) {
        List<Order> orderList = orderService.getOrderList(userId);
        return ResponseEntity.ok(orderList);
    }

    // 결제 진입 주문서 발행
    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        orderService.createOrder(orderCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 주문서 확인
    @GetMapping("/checkOrder/{orderId}")
    public ResponseEntity<?> checkOrder(@PathVariable("orderId") Long orderId) {
        OrderCheckResponse order =
                orderService.checkOrder(orderId);

        OrderCheckResponse orderCheckResponse =
                new OrderCheckResponse(order.getProductType(), order.getProductId(), order.getQuantity());

        return ResponseEntity.ok().body(orderCheckResponse);
    }

    // 주문서 업데이트
    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateOrder(@PathVariable Long orderId, @RequestBody OrderUpdateRequest orderUpdateRequest) {
        orderService.updateOrder(orderId, orderUpdateRequest);
        return ResponseEntity.ok().build();
    }

    // 주문서 삭제
//    @DeleteMapping("/{orderId}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
//        orderService.deleteOrder(orderId);
//        return ResponseEntity.noContent().build();
//    }
}