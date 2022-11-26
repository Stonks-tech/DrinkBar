package tech.stonks.drinkbar.data.drink.mapper

import tech.stonks.drinkbar.data.drink.model.DrinkDataModel
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel

class DrinkDataToDomainMapper {
    fun map(drinkDataModel: DrinkDataModel): DrinkDomainModel {
        return DrinkDomainModel(
            id = drinkDataModel.id,
            name = drinkDataModel.name,
            description = drinkDataModel.description,
            image = drinkDataModel.image,
            thumbnail = drinkDataModel.thumbnail,
        )
    }
}