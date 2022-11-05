package tech.stonks.drinkbar.presentation.drinklist.mapper

import tech.stonks.drinkbar.domain.drinklist.model.DrinkDomainModel
import tech.stonks.drinkbar.presentation.drinklist.model.DrinkPresentationModel

class DrinkDomainToPresentationMapper {
    fun map(drinkDomainModel: DrinkDomainModel): DrinkPresentationModel =
        DrinkPresentationModel(
            id = drinkDomainModel.id,
            name = drinkDomainModel.name,
            description = drinkDomainModel.description,
            thumbnail = drinkDomainModel.thumbnail,
        )
}