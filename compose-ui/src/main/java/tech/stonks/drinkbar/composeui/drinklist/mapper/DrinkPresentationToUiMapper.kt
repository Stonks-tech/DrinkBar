package tech.stonks.drinkbar.composeui.drinklist.mapper

import tech.stonks.drinkbar.composeui.drinklist.model.DrinkUiModel
import tech.stonks.drinkbar.presentation.drinklist.model.DrinkPresentationModel

class DrinkPresentationToUiMapper {
    fun map(drink: DrinkPresentationModel): DrinkUiModel {
        return DrinkUiModel(
            name = drink.name,
            description = drink.description,
            thumbnail = drink.thumbnail
        )
    }
}