package labs.prism.webcore.domain.entity;

public enum PaymentStatus {
    PENDING,   // Ожидает оплаты
    PAID,      // Оплачен
    CANCELLED, // Отменен
    FAILED     // Ошибка при оплате
}