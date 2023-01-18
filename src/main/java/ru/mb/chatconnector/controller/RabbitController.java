package ru.mb.chatconnector.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.mb.chatconnector.config.ChatConnectorProperties;
import ru.mb.chatconnector.manager.RabbitManager;
import ru.mb.chatconnector.model.incoming.RabbitMessage;

import java.io.IOException;

@Component
@Slf4j
@EnableRabbit
public class RabbitController {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private ChatConnectorProperties chatConnectorProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitManager rabbitManager;

    @RabbitListener(queues = "#{chatConnectorProperties.getQueue()}")
    public void listenMessage(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) throws IOException {
        log.info("Пришло сообщение в очередь" + chatConnectorProperties.getQueue() + ": " + new String(message.getBody()));
        rabbitManager.processMessage(
                objectMapper.readValue(message.getBody(), RabbitMessage.class),
                (String) message.getMessageProperties().getHeaders().get(AUTHORIZATION_HEADER)
        );
        channel.basicAck(tag, true);
    }
}
