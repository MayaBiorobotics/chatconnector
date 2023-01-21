package org.mb.chatconnector.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mb.chatconnector.model.incoming.RabbitMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.mb.chatconnector.config.ChatConnectorProperties;
import org.mb.chatconnector.service.RabbitManager;

import java.io.IOException;

@Component
@Slf4j
@EnableRabbit
@RequiredArgsConstructor
public class RabbitController {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final ChatConnectorProperties chatConnectorProperties;
    private final ObjectMapper objectMapper;
    private final RabbitManager rabbitManager;

    @RabbitListener(queues = "#{chatConnectorProperties.getQueue()}")
    public void listenMessage(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) throws IOException {
        log.info("Пришло сообщение в очередь " + chatConnectorProperties.getQueue() + ": " + new String(message.getBody()));
        rabbitManager.processMessage(
                objectMapper.readValue(message.getBody(), RabbitMessage.class),
                (String) message.getMessageProperties().getHeaders().get(AUTHORIZATION_HEADER)
        );
        channel.basicAck(tag, true);
    }
}
