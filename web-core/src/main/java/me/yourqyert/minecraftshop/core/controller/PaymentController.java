package me.yourqyert.minecraftshop.core.controller;

import me.yourqyert.minecraftshop.core.domain.dto.PaymentRequestDto;
import me.yourqyert.minecraftshop.core.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createPayment(
            @RequestBody PaymentRequestDto request,
            @RequestParam Long methodId) {

        String url = paymentService.initPayment(request, methodId);
        return ResponseEntity.ok(Map.of("url", url));
    }
}