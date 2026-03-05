package me.yourqyert.minecraftshop.core.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "payment_categories")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PaymentCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String icon;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<PaymentMethod> methods;
}