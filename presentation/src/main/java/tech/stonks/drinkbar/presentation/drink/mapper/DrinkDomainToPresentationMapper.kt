package tech.stonks.drinkbar.presentation.drink.mapper

import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.presentation.drink.model.DrinkPresentationModel

class DrinkDomainToPresentationMapper {
    fun map(drinkDomainModel: DrinkDomainModel): DrinkPresentationModel =
        DrinkPresentationModel(
            id = drinkDomainModel.id,
            name = drinkDomainModel.name,
            description = drinkDomainModel.description,
            thumbnail = drinkDomainModel.thumbnail,
        )
}