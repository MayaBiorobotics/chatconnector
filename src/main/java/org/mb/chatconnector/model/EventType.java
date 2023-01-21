package org.mb.chatconnector.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.mb.chatconnector.util.CustomEnumDeserializer;

@JsonDeserialize(using = CustomEnumDeserializer.class)
public enum EventType {
    CREATE_GROUP, NEW_USER, CONFIRM_CREATE_USER, CONFIRM_CREATE_DIALOG, ADD_USER_TO_DIALOGS, MESSAGE_FROM_SERVER
}
