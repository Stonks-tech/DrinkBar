package tech.stonks.drinkbar.datasource.drink.mapper

import tech.stonks.drinkbar.data.drink.model.DrinkDataModel
import tech.stonks.drinkbar.datasource.drink.model.DrinkApiDataSourceModel

class DrinkApiDataSourceToDataModelMapper {
    fun map(drinkApiDataSourceModel: DrinkApiDataSourceModel): DrinkDataModel {
        return DrinkDataModel(
            id = drinkApiDataSourceModel.id,
            name = drinkApiDataSourceModel.name,
            description = drinkApiDataSourceModel.description,
            thumbnail = drinkApiDataSourceModel.thumbnail,
            image = drinkApiDataSourceModel.image
        )
    }
}