package me.yourqyert.minecraftshop.core.repository;

import me.yourqyert.minecraftshop.core.domain.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    List<PaymentMethod> findAllByCategoryId(Long categoryId);
}