package ru.mb.chatconnector.model.outgoing;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AddUserToDialogsRequest {
    private UserDialog chatUser;
    private List<Long> dialogIds;
}
