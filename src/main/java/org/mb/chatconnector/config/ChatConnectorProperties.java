package org.mb.chatconnector.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "chat.connector"
)
@Getter
@Setter
public class ChatConnectorProperties {
    private String serverToken;
    private String queue;
}
