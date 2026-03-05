package me.yourqyert.minecraftshop.core.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment_methods")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String icon;

    @Column(length = 1000)
    private String gatewayUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private PaymentCategory category;
}