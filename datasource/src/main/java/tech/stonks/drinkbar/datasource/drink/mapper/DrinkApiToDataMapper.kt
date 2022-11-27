package tech.stonks.drinkbar.datasource.drink.mapper

import tech.stonks.drinkbar.data.drink.model.DrinkDataModel
import tech.stonks.drinkbar.datasource.drink.model.DrinkApiModel

@Suppress("UNCHECKED_CAST")
class DrinkApiToDataMapper(private val _ingredientMapper: IngredientStringsToDataMapper) {
    fun map(drinkApiDataSourceModel: DrinkApiModel): DrinkDataModel {
        return DrinkDataModel(
            id = drinkApiDataSourceModel.id,
            name = drinkApiDataSourceModel.name,
            description = drinkApiDataSourceModel.description,
            thumbnail = drinkApiDataSourceModel.thumbnail,
            image = drinkApiDataSourceModel.image,
            ingredients = (listOf(
                drinkApiDataSourceModel.ingredient1 to drinkApiDataSourceModel.measure1,
                drinkApiDataSourceModel.ingredient2 to drinkApiDataSourceModel.measure2,
                drinkApiDataSourceModel.ingredient3 to drinkApiDataSourceModel.measure3,
                drinkApiDataSourceModel.ingredient4 to drinkApiDataSourceModel.measure4,
                drinkApiDataSourceModel.ingredient5 to drinkApiDataSourceModel.measure5,
                drinkApiDataSourceModel.ingredient6 to drinkApiDataSourceModel.measure6,
                drinkApiDataSourceModel.ingredient7 to drinkApiDataSourceModel.measure7,
                drinkApiDataSourceModel.ingredient8 to drinkApiDataSourceModel.measure8,
                drinkApiDataSourceModel.ingredient9 to drinkApiDataSourceModel.measure9,
                drinkApiDataSourceModel.ingredient10 to drinkApiDataSourceModel.measure10,
                drinkApiDataSourceModel.ingredient11 to drinkApiDataSourceModel.measure11,
                drinkApiDataSourceModel.ingredient12 to drinkApiDataSourceModel.measure12,
                drinkApiDataSourceModel.ingredient13 to drinkApiDataSourceModel.measure13,
                drinkApiDataSourceModel.ingredient14 to drinkApiDataSourceModel.measure14,
                drinkApiDataSourceModel.ingredient15 to drinkApiDataSourceModel.measure15,
            ).filter { it.first != null } as List<Pair<String, String?>>)
                .map(_ingredientMapper::map)
        )
    }

}