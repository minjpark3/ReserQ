package com.example.mo_payment.payment.service;


import com.example.mo_payment.core.ProductType;
import com.example.mo_payment.order.dto.OrderCheckResponse;
import com.example.mo_payment.order.dto.OrderCreateRequest;
import com.example.mo_payment.order.dto.OrderUpdateRequest;
import com.example.mo_payment.order.service.OrderService;
import com.example.mo_payment.client.ProductClient;
import com.example.mo_payment.payment.domain.Payment;
import com.example.mo_payment.payment.domain.PaymentRepository;
import com.example.mo_payment.payment.dto.EnterPaymentRequest;
import com.example.mo_payment.payment.dto.PaymentCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ProductClient productClient;
    private final OrderService orderService;


    // 일반 상품 결제 진입
    @Transactional
    public Integer enterPaymentProduct(Long userId, Long productId,
                                       EnterPaymentRequest enterPaymentRequest) {
        // 구매 수량과 재고 비교
        Integer productStock = productClient.getProductStock(productId);

        if (productStock <= 0) {
            throw new RuntimeException("구매할 수 있는 상품이 없습니다.");
        }

        if (productStock < enterPaymentRequest.getQuantity()) {
            throw new RuntimeException("구매 수량은 상품의 재고를 넘을 수 없습니다.");
        }


        // 주문서 발행
        orderService.createOrder(
                new OrderCreateRequest(
                        userId, productId, ProductType.NORMAL, enterPaymentRequest.getQuantity()));

        // 총 결제 금액 반환
        Integer productPrice =
                productClient.getProductPrice(productId);

        return productPrice * enterPaymentRequest.getQuantity();
    }


    // 상품 결제
    @Transactional
    public void payment(Long orderId, PaymentCreateRequest paymentCreateRequest) {
        OrderCheckResponse orderCheckResponse = orderService.checkOrder(orderId);

        System.out.println("1");
        // 결제 실패 시나리오
        if (Math.random() <= 0.2) {
            System.out.println("0");
            orderService.deleteOrder(orderId);
            throw new RuntimeException("결제 실패");
        }

        System.out.println("1");
        // 재고 감소
        productClient.decreasedProductStock(orderCheckResponse.getProductId(), orderCheckResponse.getQuantity());
//        System.out.println("1");
//        switch (order.getProductType()) {
//            case NORMAL -> productClient.decreasedProductStock(
//                    order.getProductId(), order.getQuantity());

//            case RESERVED -> productClient.decreasedReservedProductStock(
//                    String.valueOf(order.getProductId()), String.valueOf(order.getQuantity()));
//        }
        System.out.println("1");
        // 결제서 발행
        Payment payment = Payment.builder()
                .orderId(orderId)
                .totalPayment(Long.valueOf(paymentCreateRequest.getTotalPayment()))
                .build();
        System.out.println("1");
        Payment savedPayment = paymentRepository.save(payment);
        System.out.println("1");
        // 주문서 업데이트
        orderService.updateOrder(orderId, new OrderUpdateRequest(savedPayment.getId(), true));
        System.out.println("1");
    }
}
