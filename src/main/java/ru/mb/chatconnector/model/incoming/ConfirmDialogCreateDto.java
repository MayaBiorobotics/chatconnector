package ru.mb.chatconnector.model.incoming;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConfirmDialogCreateDto {
    private Long chatUserId;
    private Long chatDialogId;
}
