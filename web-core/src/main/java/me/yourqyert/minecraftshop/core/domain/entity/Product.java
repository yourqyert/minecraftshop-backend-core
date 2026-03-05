package me.yourqyert.minecraftshop.core.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название товара обязательно")
    @Column(nullable = false)
    private String title;

    @NotNull(message = "Цена обязательна")
    @Positive(message = "Цена должна быть положительной")
    @Column(nullable = false)
    private BigDecimal price;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_commands", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "command")
    @Builder.Default
    private List<String> commands = new ArrayList<>();

    @NotBlank(message = "Категория обязательна")
    private String category;

    private String icon;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private Integer amount;

    public enum ProductType {
        ITEM, CURRENCY
    }
}