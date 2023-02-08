package org.mb.chatconnector.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConnectorConfig {

    @Bean
    public ChatConnectorProperties chatConnectorProperties() {
        return new ChatConnectorProperties();
    }

}
