package me.yourqyert.minecraftshop.core.service;

import me.yourqyert.minecraftshop.core.domain.entity.Delivery;
import me.yourqyert.minecraftshop.core.domain.entity.DeliveryStatus;
import me.yourqyert.minecraftshop.core.domain.entity.Payment;
import me.yourqyert.minecraftshop.core.domain.entity.Product;
import me.yourqyert.minecraftshop.core.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public void scheduleDelivery(Payment payment, Product product) {

        for (String cmd : product.getCommands()) {
            String finalCommand = cmd.replace("{player}", payment.getNickname());

            if (product.getAmount() != null) {
                finalCommand = finalCommand.replace("{amount}", String.valueOf(product.getAmount()));
            }

            Delivery delivery = Delivery.builder()
                    .paymentId(payment.getId())
                    .nickname(payment.getNickname())
                    .command(finalCommand)
                    .status(DeliveryStatus.WAITING)
                    .build();

            deliveryRepository.save(delivery);
        }
    }

    public List<Delivery> getPendingDeliveries() {
        return deliveryRepository.findAllByStatus(DeliveryStatus.WAITING);
    }

    @Transactional
    public void markAsDone(Long deliveryId) {
        deliveryRepository.findById(deliveryId).ifPresent(delivery -> {
            delivery.setStatus(DeliveryStatus.DONE);
            deliveryRepository.save(delivery);
        });
    }
}