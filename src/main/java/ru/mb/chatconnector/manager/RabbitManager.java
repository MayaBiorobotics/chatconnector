package ru.mb.chatconnector.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mb.chatconnector.config.ChatConnectorProperties;
import ru.mb.chatconnector.model.AuthorizationException;
import ru.mb.chatconnector.model.incoming.RabbitMessage;
import ru.mb.chatconnector.model.incoming.ConfirmDialogCreateDto;
import ru.mb.chatconnector.model.incoming.ConfirmUserDto;

@Service
public class RabbitManager {

    @Autowired
    private ChatConnectorProperties properties;

    @Autowired
    private ObjectMapper mapper;

    @Autowired(required = false)
    private ChatConnector chatConnector;

    public void processMessage(RabbitMessage rabbitMessage, String authorizationToken) {
        if (authorizationToken == null || !authorizationToken.substring(7).equals(properties.getToken())) {
            throw new AuthorizationException("Токен не указан или является ошибочным");
        }

        Object payload = rabbitMessage.getPayload();
        if (payload == null) {
            throw new IllegalArgumentException("payload не должно быть пустым");
        }

        switch (rabbitMessage.getType()) {
            case CONFIRM_CREATE_USER -> {
                ConfirmUserDto confirmUserDto = mapper.convertValue(payload, ConfirmUserDto.class);
                chatConnector.onConfirmUserCreate(confirmUserDto.getUsername(), confirmUserDto.getChatUserId());
            }
            case CONFIRM_CREATE_DIALOG -> {
                ConfirmDialogCreateDto dialogCreateDto = mapper.convertValue(payload, ConfirmDialogCreateDto.class);
                chatConnector.onConfirmDialogCreate(dialogCreateDto.getChatDialogId(), dialogCreateDto.getChatUserId());
            }
            default -> throw new IllegalArgumentException("Неподдерживаемый тип");
        }
    }
}
