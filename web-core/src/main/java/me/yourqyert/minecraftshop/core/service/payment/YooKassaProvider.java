package me.yourqyert.minecraftshop.core.service.payment;

import me.yourqyert.minecraftshop.core.domain.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class YooKassaProvider implements PaymentProvider {

    @Override
    public String createCheckoutUrl(Payment payment) {
        // yookassa api request logic
        return "https://yookassa.ru/checkout?id=" + payment.getId();
    }

    @Override
    public String getProviderName() {
        return "YOOKASSA";
    }
}