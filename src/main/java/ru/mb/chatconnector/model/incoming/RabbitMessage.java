package ru.mb.chatconnector.model.incoming;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.mb.chatconnector.model.EventType;

@Data
@Accessors(chain = true)
public class RabbitMessage<T> {
    private EventType type;
    private T payload;
}
