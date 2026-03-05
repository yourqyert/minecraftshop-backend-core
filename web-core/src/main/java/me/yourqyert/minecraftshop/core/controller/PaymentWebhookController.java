package me.yourqyert.minecraftshop.core.controller;

import me.yourqyert.minecraftshop.core.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/payments/webhook")
@RequiredArgsConstructor
public class PaymentWebhookController {

    private final PaymentService paymentService;

    @PostMapping("/yookassa")
    public ResponseEntity<Void> handleYookassa(@RequestBody Map<String, Object> notification) {

        try {
            Map<String, Object> object = (Map<String, Object>) notification.get("object");
            String status = (String) object.get("status");
            String externalId = (String) object.get("id");

            if ("succeeded".equals(status)) {
                paymentService.confirmPayment(externalId);
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}