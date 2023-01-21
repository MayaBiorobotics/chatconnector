package org.mb.chatconnector.model.outgoing;

import lombok.Data;
import lombok.experimental.Accessors;
import org.mb.chatconnector.model.outgoing.nested.UserDialog;

import java.util.List;

@Data
@Accessors(chain = true)
public class ChatChangeCanWriteRightsRequest {
    private List<UserDialog> users;
}
