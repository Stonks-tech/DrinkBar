package tech.stonks.drinkbar.presentation.drink.mapper

import tech.stonks.drinkbar.domain.drink.model.IngredientDomainModel
import tech.stonks.drinkbar.presentation.drink.model.IngredientPresentationModel

class IngredientDomainToPresentationMapper {
    fun map(ingredient: IngredientDomainModel): IngredientPresentationModel {
        return IngredientPresentationModel(
            name = ingredient.name,
            measure = ingredient.measure,
        )
    }
}