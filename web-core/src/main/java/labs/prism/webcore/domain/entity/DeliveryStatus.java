package labs.prism.webcore.domain.entity;

public enum DeliveryStatus {
    WAITING, // Ожидает плагин
    DONE,    // Выдано
    ERROR    // Ошибка (например, нет места в инвентаре)
}