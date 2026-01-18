package labs.prism.webcore.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "servers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String secretKey; // Ключ, который прописывается в конфиге плагина
}