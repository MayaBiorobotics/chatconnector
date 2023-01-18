package ru.mb.chatconnector.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = CustomEnumDeserializer.class)
public enum EventType {
    CREATE_GROUP, NEW_USER, CONFIRM_CREATE_USER, CONFIRM_CREATE_DIALOG, ADD_USER_TO_DIALOGS, MESSAGE_FROM_SERVER
}
