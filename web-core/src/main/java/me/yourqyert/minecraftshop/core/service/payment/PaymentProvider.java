package me.yourqyert.minecraftshop.core.service.payment;

import me.yourqyert.minecraftshop.core.domain.entity.Payment;

public interface PaymentProvider {
    String createCheckoutUrl(Payment payment);
    String getProviderName();
}