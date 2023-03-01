package org.mb.chatconnector.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mb.chatconnector.model.incoming.ConfirmDialogCreateDto;
import org.mb.chatconnector.model.incoming.ConfirmUserDto;
import org.mb.chatconnector.model.incoming.RabbitMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RabbitManager {

    private final String serverToken;
    private final ObjectMapper mapper;
    private final ChatConnector chatConnector;

    public RabbitManager(@Value("${chat.connector.server-token}") String serverToken,
                         ObjectMapper mapper,
                         Optional<ChatConnector> chatConnector) {
        this.serverToken = serverToken;
        this.mapper = mapper;
        this.chatConnector = chatConnector.orElse(null);
    }

    public void processMessage(RabbitMessage rabbitMessage, String authorizationToken) {
        if (authorizationToken == null || !authorizationToken.substring(7).equals(serverToken)) {
            throw new IllegalArgumentException("Токен не указан или является ошибочным");
        }

        Object payload = rabbitMessage.getPayload();
        if (payload == null) {
            throw new IllegalArgumentException("payload не должно быть пустым");
        }

        switch (rabbitMessage.getType()) {
            case CONFIRM_CREATE_USER:
                ConfirmUserDto confirmUserDto = mapper.convertValue(payload, ConfirmUserDto.class);
                chatConnector.onConfirmUserCreate(confirmUserDto.getUsername(), confirmUserDto.getChatUserId());
                break;
            case CONFIRM_CREATE_DIALOG:
                ConfirmDialogCreateDto dialogCreateDto = mapper.convertValue(payload, ConfirmDialogCreateDto.class);
                chatConnector.onConfirmDialogCreate(dialogCreateDto.getChatDialogId(), dialogCreateDto.getChatUserId(), dialogCreateDto.getMeta());
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый тип");
        }
    }
}
