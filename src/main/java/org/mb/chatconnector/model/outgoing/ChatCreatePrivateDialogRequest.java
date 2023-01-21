package org.mb.chatconnector.model.outgoing;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChatCreatePrivateDialogRequest {
    private Long firstUserId;
    private Long secondUserId;
    private String meta;
}
