package org.mb.chatconnector.model.outgoing;

import lombok.Data;
import lombok.experimental.Accessors;
import org.mb.chatconnector.model.outgoing.nested.UserDialog;

import java.util.List;

@Data
@Accessors(chain = true)
public class ChatCreateGroupRequest {
    private List<UserDialog> users;
    private UserDialog authUser;
    private String name;
    private boolean includeSystemMessage;
    private String meta;
}
