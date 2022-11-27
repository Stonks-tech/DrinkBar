package tech.stonks.drinkbar.presentation.drink.mapper

import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.presentation.drink.model.DrinkPresentationModel

class DrinkDomainToPresentationMapper(private val _ingredientMapper: IngredientDomainToPresentationMapper) {
    fun map(drinkDomainModel: DrinkDomainModel): DrinkPresentationModel =
        DrinkPresentationModel(
            id = drinkDomainModel.id,
            name = drinkDomainModel.name,
            description = drinkDomainModel.description,
            thumbnail = drinkDomainModel.thumbnail,
            ingredients = drinkDomainModel.ingredients.map { _ingredientMapper.map(it) },
        )
}