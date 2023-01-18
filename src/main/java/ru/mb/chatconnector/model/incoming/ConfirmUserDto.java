package ru.mb.chatconnector.model.incoming;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConfirmUserDto {
    private Long chatUserId;
    private String username;
}
