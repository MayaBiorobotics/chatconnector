package ru.mb.chatconnector.model.outgoing;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SendServerMessageRequest {
    private Long userId;
    private Long dialogId;
    private String content;
}