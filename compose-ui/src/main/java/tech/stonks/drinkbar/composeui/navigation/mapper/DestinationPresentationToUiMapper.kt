package tech.stonks.drinkbar.xml_ui.navigation.mapper

import tech.stonks.drinkbar.presentation.architecture.model.PresentationDestination
import tech.stonks.drinkbar.xml_ui.navigation.model.UiDestination

interface DestinationPresentationToUiMapper {
    fun toUi(input: PresentationDestination): UiDestination
}