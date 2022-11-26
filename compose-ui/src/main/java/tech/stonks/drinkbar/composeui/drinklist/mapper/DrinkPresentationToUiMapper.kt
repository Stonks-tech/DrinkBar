package tech.stonks.drinkbar.composeui.drinklist.mapper

import tech.stonks.drinkbar.composeui.drinklist.model.DrinkUiModel
import tech.stonks.drinkbar.presentation.drink.model.DrinkPresentationModel

class DrinkPresentationToUiMapper {
    fun map(drink: DrinkPresentationModel): DrinkUiModel {
        return DrinkUiModel(
            id = drink.id,
            name = drink.name,
            description = drink.description,
            thumbnail = drink.thumbnail
        )
    }
}