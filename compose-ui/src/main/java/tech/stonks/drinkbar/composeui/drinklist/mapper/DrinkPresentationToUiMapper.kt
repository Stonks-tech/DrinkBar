package tech.stonks.drinkbar.composeui.drinklist.mapper

import tech.stonks.drinkbar.composeui.drinklist.model.DrinkUiModel
import tech.stonks.drinkbar.presentation.drink.model.DrinkPresentationModel

class DrinkPresentationToUiMapper {
    fun map(drink: DrinkPresentationModel): DrinkUiModel {
        return DrinkUiModel(
            id = drink.id,
            name = drink.name,
            description = drink.description,
            thumbnail = drink.thumbnail,
            ingredients = drink.ingredients.map {
                buildString {
                    append(it.name)
                    if (it.measure != null) {
                        append(" (${it.measure})")
                    }
                }
            }
        )
    }
}