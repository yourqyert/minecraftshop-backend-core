package me.yourqyert.minecraftshop.core.domain.dto;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private String nickname;
    private Long productId;
}