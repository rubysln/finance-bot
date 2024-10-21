package app.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@PropertySource("bot.properties")
public class BotConfig {
    @Value("${name}")
    private String name;

    @Value("${token}")
    private String token;
}