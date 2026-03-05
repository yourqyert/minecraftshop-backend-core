package me.yourqyert.minecraftshop.core.service;

import me.yourqyert.minecraftshop.core.domain.entity.Payment;
import me.yourqyert.minecraftshop.core.domain.entity.PaymentStatus;
import me.yourqyert.minecraftshop.core.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final PaymentRepository paymentRepository;

    public List<Payment> getLastPurchases() {
        return paymentRepository.findTop10ByStatusOrderByCreatedAtDesc(PaymentStatus.PAID);
    }

    public List<DonatorDto> getTopDonators() {
        return paymentRepository.findTopDonators(PageRequest.of(0, 10))
                .stream()
                .map(obj -> new DonatorDto((String) obj[0], (BigDecimal) obj[1]))
                .collect(Collectors.toList());
    }

    public record DonatorDto(String nickname, BigDecimal totalAmount) {}
}