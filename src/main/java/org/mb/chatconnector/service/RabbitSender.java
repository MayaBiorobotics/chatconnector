package org.mb.chatconnector.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mb.chatconnector.model.EventType;
import org.mb.chatconnector.model.incoming.RabbitMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.json.simple.JSONValue.parse;

@Service
@Slf4j
public class RabbitSender {

    private static final String CHAT_QUEUE_NAME = "chat-application-messages";
    private final String serverToken;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    public RabbitSender(@Value("${chat.connector.server-token}") String serverToken,
                        RabbitTemplate rabbitTemplate,
                        ObjectMapper mapper) {
        this.serverToken = serverToken;
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    public void send(EventType type, Object payload) {
        RabbitMessage<Object> rabbitMessage = new RabbitMessage<>();
        rabbitMessage.setType(type);
        rabbitMessage.setPayload(payload);

        try {
            rabbitTemplate.convertAndSend(
                    CHAT_QUEUE_NAME,
                    parse(mapper.writeValueAsString(rabbitMessage)),
                    message -> {
                        message.getMessageProperties().setHeader("Authorization", "Bearer " + serverToken);
                        return message;
                    }
            );

            log.info("Сообщение отправлено в очередь: " + CHAT_QUEUE_NAME);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
