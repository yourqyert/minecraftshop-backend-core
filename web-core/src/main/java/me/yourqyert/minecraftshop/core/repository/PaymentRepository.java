package me.yourqyert.minecraftshop.core.repository;

import me.yourqyert.minecraftshop.core.domain.entity.Payment;
import me.yourqyert.minecraftshop.core.domain.entity.PaymentStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    Optional<Payment> findByExternalId(String externalId);

    List<Payment> findTop10ByStatusOrderByCreatedAtDesc(PaymentStatus status);

    @Query("SELECT p.nickname, SUM(p.amount) as total FROM Payment p " +
            "WHERE p.status = 'PAID' " +
            "GROUP BY p.nickname " +
            "ORDER BY total DESC")
    List<Object[]> findTopDonators(Pageable pageable);
}