package ru.mb.chatconnector.model.outgoing;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CreateGroupRequest {
    private List<UserDialog> users;
    private UserDialog authUser;
    private String name;
    private boolean includeSystemMessage;
    private String meta;
}
