package labs.prism.webcore.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "site_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteSettings {

    @Id
    private Long id = 1L; // Всегда одна запись

    private String serverName;
    private String serverIp;
    private String vkLink;
    private String discordLink;
    private String youtubeLink;

    // Настройки темы (цвета можно передавать строками HEX)
    private String primaryColor;
    private String accentColor;
}