package tech.stonks.drinkbar.xml_ui.architecture.mapper

import tech.stonks.drinkbar.xml_ui.architecture.model.NotificationUiModel

interface NotificationPresentationToUiMapper<PRESENTATION_NOTIFICATION : Any> {
    fun toUi(presentationNotification: PRESENTATION_NOTIFICATION): NotificationUiModel
}