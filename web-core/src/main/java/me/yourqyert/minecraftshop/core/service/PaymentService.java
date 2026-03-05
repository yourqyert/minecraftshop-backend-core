package me.yourqyert.minecraftshop.core.service;

import me.yourqyert.minecraftshop.core.domain.dto.PaymentRequestDto;
import labs.prism.webcore.domain.entity.*;
import me.yourqyert.minecraftshop.core.domain.entity.Payment;
import me.yourqyert.minecraftshop.core.domain.entity.PaymentMethod;
import me.yourqyert.minecraftshop.core.domain.entity.PaymentStatus;
import me.yourqyert.minecraftshop.core.domain.entity.Product;
import me.yourqyert.minecraftshop.core.repository.PaymentMethodRepository;
import me.yourqyert.minecraftshop.core.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ProductService productService;
    private final DeliveryService deliveryService;
    private final PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public String initPayment(PaymentRequestDto request, Long methodId) {
        Product product = productService.getProductById(request.getProductId());

        PaymentMethod method = paymentMethodRepository.findById(methodId)
                .orElseThrow(() -> new RuntimeException("Payment method not found"));

        Payment payment = Payment.builder()
                .nickname(request.getNickname())
                .amount(product.getPrice())
                .productId(product.getId())
                .provider(method.getName())
                .status(PaymentStatus.PENDING)
                .build();

        paymentRepository.save(payment);

        return method.getGatewayUrl()
                .replace("{amount}", product.getPrice().toString())
                .replace("{nickname}", request.getNickname())
                .replace("{paymentId}", payment.getId().toString());
    }

    @Transactional
    public void confirmPayment(String externalId) {
        Payment payment = paymentRepository.findByExternalId(externalId)
                .orElseThrow(() -> new RuntimeException("Payment not found: " + externalId));

        if (payment.getStatus() == PaymentStatus.PENDING) {
            payment.setStatus(PaymentStatus.PAID);
            paymentRepository.save(payment);

            Product product = productService.getProductById(payment.getProductId());
            deliveryService.scheduleDelivery(payment, product);
        }
    }
}