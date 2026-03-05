package me.yourqyert.minecraftshop.core.domain.entity;

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
    private Long id = 1L;

    private String serverName;
    private String serverIp;
    private String vkLink;
    private String discordLink;
    private String youtubeLink;

    private String primaryColor;
    private String accentColor;
}