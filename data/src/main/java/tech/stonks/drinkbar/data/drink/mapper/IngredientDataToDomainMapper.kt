package tech.stonks.drinkbar.data.drink.mapper

import tech.stonks.drinkbar.data.drink.model.IngredientDataModel
import tech.stonks.drinkbar.domain.drink.model.IngredientDomainModel

class IngredientDataToDomainMapper {
    fun map(ingredientDataModel: IngredientDataModel): IngredientDomainModel {
        return IngredientDomainModel(
            name = ingredientDataModel.name,
            measure = ingredientDataModel.measure,
        )
    }
}