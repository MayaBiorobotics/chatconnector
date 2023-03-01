package org.mb.chatconnector.service;


public interface ChatConnector {
    default void onConfirmUserCreate(String username, Long chatUserId) {
    }

    default void onConfirmDialogCreate(Long chatDialogId, Long chatUserId, String meta) {
    }
}
