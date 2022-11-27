package tech.stonks.drinkbar.datasource.drink.mapper

import tech.stonks.drinkbar.data.drink.model.IngredientDataModel

class IngredientStringsToDataMapper {
    fun map(ingredientStrings: Pair<String, String?>): IngredientDataModel {
        return IngredientDataModel(ingredientStrings.first, ingredientStrings.second)
    }
}