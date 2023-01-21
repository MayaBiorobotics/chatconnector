package org.mb.chatconnector.model.incoming;

import lombok.Data;
import lombok.experimental.Accessors;
import org.mb.chatconnector.model.EventType;

@Data
@Accessors(chain = true)
public class RabbitMessage<T> {
    private EventType type;
    private T payload;
}
