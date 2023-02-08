package org.mb.chatconnector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConnectorConfig {

    @Bean
    public ChatConnectorProperties chatConnectorProperties() {
        return new ChatConnectorProperties();
    }

}
