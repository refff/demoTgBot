package com.example.demo.congiguration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "telegram-bot")
@Getter
@Setter
public class BotConfig {
    private String token;
    private String name;
    private String url;
}
