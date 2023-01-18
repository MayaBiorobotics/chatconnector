package ru.mb.chatconnector.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mb.chatconnector.config.ChatConnectorProperties;
import ru.mb.chatconnector.model.EventType;
import ru.mb.chatconnector.model.incoming.RabbitMessage;

import static org.json.simple.JSONValue.parse;

@Service
@Slf4j
public class RabbitSender {

    private static final String CHAT_QUEUE_NAME = "chat-application-messages";

    @Autowired
    private ChatConnectorProperties properties;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper mapper;

    public void send(EventType type, Object payload) {
        RabbitMessage<Object> rabbitMessage = new RabbitMessage<>();
        rabbitMessage.setType(type);
        rabbitMessage.setPayload(payload);

        final MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setHeader("Authorization", "Bearer " + properties.getToken());
            return message;
        };

        try {
            rabbitTemplate.convertAndSend(
                    CHAT_QUEUE_NAME,
                    parse(mapper.writeValueAsString(rabbitMessage)),
                    messagePostProcessor
            );

            log.info("Сообщение отправлено в очередь: " + CHAT_QUEUE_NAME);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
