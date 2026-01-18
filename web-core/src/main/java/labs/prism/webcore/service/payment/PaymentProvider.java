package labs.prism.webcore.service.payment;

import labs.prism.webcore.domain.entity.Payment;

public interface PaymentProvider {
    String createCheckoutUrl(Payment payment);
    String getProviderName();
}