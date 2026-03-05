package me.yourqyert.minecraftshop.core.service.payment;

import me.yourqyert.minecraftshop.core.domain.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class YooMoneyProvider implements PaymentProvider {

    @Override
    public String createCheckoutUrl(Payment payment) {
        // yoomoney api request logic
        return "https://yoomoney.ru/transfer/quickpay?receiver=1111222233334444&sum=" + payment.getAmount();
    }

    @Override
    public String getProviderName() {
        return "YOOMONEY";
    }
}