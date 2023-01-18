package ru.mb.chatconnector.manager;


public interface ChatConnector {
    default void onConfirmUserCreate(String username, Long chatUserId) {
    }

    default void onConfirmDialogCreate(Long chatDialogId, Long chatUserId) {
    }
}
