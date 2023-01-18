package ru.mb.chatconnector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.mb.chatconnector.config.ChatConnectorProperties;

@SpringBootApplication
public class ChatConnectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatConnectorApplication.class, args);
    }

}
