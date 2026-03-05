package me.yourqyert.minecraftshop.core.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID paymentId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String command;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private int attempts;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = DeliveryStatus.WAITING;
        this.attempts = 0;
    }
}