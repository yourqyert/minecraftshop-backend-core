package labs.prism.webcore.service;

import labs.prism.webcore.domain.entity.Delivery;
import labs.prism.webcore.domain.entity.DeliveryStatus;
import labs.prism.webcore.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public void scheduleDelivery(labs.prism.webcore.domain.entity.Payment payment, List<String> commands) {
        for (String cmd : commands) {
            String finalCommand = cmd.replace("{player}", payment.getNickname());
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