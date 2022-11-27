package tech.stonks.drinkbar.composeui.drinklist.mapper

import tech.stonks.drinkbar.xml_ui.navigation.mapper.DestinationPresentationToUiMapper
import tech.stonks.drinkbar.xml_ui.navigation.model.UiDestination

interface DrinkListDestinationToUiMapper : DestinationPresentationToUiMapper {
    abstract class DrinkDetailsDestination : UiDestination {
        abstract val drinkId: String
    }
}