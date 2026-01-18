package labs.prism.webcore.repository;

import labs.prism.webcore.domain.entity.Delivery;
import labs.prism.webcore.domain.entity.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    /**
     * Поиск всех команд со статусом WAITING для выдачи плагином.
     */
    List<Delivery> findAllByStatus(DeliveryStatus status);
}