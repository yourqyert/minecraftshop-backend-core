package me.yourqyert.minecraftshop.core.repository;

import me.yourqyert.minecraftshop.core.domain.entity.PaymentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentCategoryRepository extends JpaRepository<PaymentCategory, Long> {
}