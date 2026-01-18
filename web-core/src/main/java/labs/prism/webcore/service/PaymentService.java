package labs.prism.webcore.service;

import labs.prism.webcore.domain.dto.PaymentRequestDto;
import labs.prism.webcore.domain.entity.Payment;
import labs.prism.webcore.domain.entity.PaymentStatus;
import labs.prism.webcore.domain.entity.Product;
import labs.prism.webcore.repository.PaymentRepository;
import labs.prism.webcore.service.payment.PaymentProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ProductService productService;
    private final DeliveryService deliveryService;
    private final Map<String, PaymentProvider> providers;

    public PaymentService(PaymentRepository paymentRepository,
                          ProductService productService,
                          List<PaymentProvider> providerList) {
        this.paymentRepository = paymentRepository;
        this.productService = productService;
        this.providers = providerList.stream()
                .collect(Collectors.toMap(PaymentProvider::getProviderName, Function.identity()));
    }

    @Transactional
    public String initPayment(PaymentRequestDto request, String providerName) {
        Product product = productService.getProductById(request.getProductId());

        Payment payment = Payment.builder()
                .nickname(request.getNickname())
                .amount(product.getPrice())
                .productId(product.getId())
                .provider(providerName)
                .status(PaymentStatus.PENDING)
                .build();

        paymentRepository.save(payment);

        PaymentProvider provider = providers.get(providerName);
        if (provider == null) {
            throw new RuntimeException("Provider " + providerName + " not found");
        }

        return provider.createCheckoutUrl(payment);
    }

    @Transactional
    public void confirmPayment(String externalId) {
        Payment payment = paymentRepository.findByExternalId(externalId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (payment.getStatus() == PaymentStatus.PENDING) {
            payment.setStatus(PaymentStatus.PAID);
            paymentRepository.save(payment);

            // Получаем продукт, чтобы узнать его команды
            Product product = productService.getProductById(payment.getProductId());

            // Отправляем команды в очередь выдачи
            deliveryService.scheduleDelivery(payment, product.getCommands());
        }
    }
}