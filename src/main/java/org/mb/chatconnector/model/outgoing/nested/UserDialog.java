package org.mb.chatconnector.model.outgoing.nested;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDialog {
    private Long userId;
    private boolean canWrite;
}