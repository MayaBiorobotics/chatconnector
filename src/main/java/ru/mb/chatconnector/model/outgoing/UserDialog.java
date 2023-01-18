package ru.mb.chatconnector.model.outgoing;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDialog {
    private Long userId;
    private boolean canWrite;
}