package com.example.mo_payment.payment.controller;

import com.example.mo_payment.payment.dto.EnterPaymentRequest;
import com.example.mo_payment.payment.dto.PaymentCreateRequest;
import com.example.mo_payment.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

//    @Autowired
//    public PaymentController(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }

    // 일반 상품 결제 진입
    @PostMapping("/products/{userId}/{productId}")
    public ResponseEntity<?> enterPaymentProduct(@PathVariable Long userId,
                                                 @PathVariable Long productId,
                                                 @Valid @RequestBody EnterPaymentRequest enterPaymentRequest) {

        Integer totalPayment = paymentService.enterPaymentProduct(userId,productId , enterPaymentRequest);
        PaymentCreateRequest paymentCreateRequest = new PaymentCreateRequest(totalPayment);

        return ResponseEntity.ok().body(paymentCreateRequest);
    }

//    //
//    // 상품 결제 진입 엔드포인트
//    @PostMapping("/product/{userId}")
//    public Integer enterPaymentProduct(@RequestParam Long userId, @RequestParam Long productId,
//                                       @RequestBody EnterPaymentRequest enterPaymentRequest) {
//        return paymentService.enterPaymentProduct(userId, productId, enterPaymentRequest);
//    }


//    // 예약 상품 결제 진입 엔드포인트
//    @PostMapping("/reserved")
//    public Integer enterPaymentReservedProduct(@RequestParam Long userId, @RequestParam Long reservedProductId,
//                                               @RequestBody EnterPaymentRequest enterPaymentRequest) {
//        return paymentService.enterPaymentReservedProduct(userId, reservedProductId, enterPaymentRequest);
//    }

    // 상품 결제 엔드포인트
    @PostMapping("/orders/{orderId}")
    public ResponseEntity<String> processPayment(@PathVariable Long orderId, @RequestBody PaymentCreateRequest paymentCreateRequest) {

        try {
            paymentService.payment(orderId, paymentCreateRequest);
            return ResponseEntity.ok("결제 성공 ");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}