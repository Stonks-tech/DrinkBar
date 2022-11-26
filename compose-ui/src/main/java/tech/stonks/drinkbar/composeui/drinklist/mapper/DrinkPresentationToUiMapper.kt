package tech.stonks.drinkbar.composeui.drinklist.mapper

import tech.stonks.drinkbar.presentation.drinklist.model.DrinkPresentationModel
import tech.stonks.drinkbar.composeui.drinklist.model.DrinkUiModel

class DrinkPresentationToUiMapper {
    fun map(drink: DrinkPresentationModel): DrinkUiModel {
        return DrinkUiModel(
            name = drink.name,
            thumbnail = drink.thumbnail
        )
    }
}