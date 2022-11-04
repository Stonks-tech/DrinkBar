package tech.stonks.drinkbar.xml_ui.drinklist.mapper

import tech.stonks.drinkbar.presentation.drinklist.model.DrinkPresentationModel
import tech.stonks.drinkbar.xml_ui.drinklist.model.DrinkUiModel

class DrinkPresentationToUiMapper {
    fun map(drink: DrinkPresentationModel): DrinkUiModel {
        return DrinkUiModel(
            name = drink.name,
            thumbnail = drink.thumbnail
        )
    }
}