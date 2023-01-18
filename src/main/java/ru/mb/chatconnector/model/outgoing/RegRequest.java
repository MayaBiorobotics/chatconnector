package ru.mb.chatconnector.model.outgoing;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private String token;
}
