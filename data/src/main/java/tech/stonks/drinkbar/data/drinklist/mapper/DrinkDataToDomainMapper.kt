package tech.stonks.drinkbar.data.drinklist.mapper

import tech.stonks.drinkbar.data.drink.model.DrinkDataModel
import tech.stonks.drinkbar.domain.drinklist.model.DrinkDomainModel

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