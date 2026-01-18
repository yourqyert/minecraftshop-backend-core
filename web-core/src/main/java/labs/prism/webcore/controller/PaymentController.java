package labs.prism.webcore.controller;

import labs.prism.webcore.domain.dto.PaymentRequestDto;
import labs.prism.webcore.service.PaymentService;
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
            @RequestParam String provider) {

        String url = paymentService.initPayment(request, provider.toUpperCase());
        return ResponseEntity.ok(Map.of("url", url));
    }
}