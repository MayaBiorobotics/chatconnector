package org.mb.chatconnector.model.outgoing;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChatSendServerMessageRequest {
    private Long userId;
    private Long dialogId;
    private String content;
}