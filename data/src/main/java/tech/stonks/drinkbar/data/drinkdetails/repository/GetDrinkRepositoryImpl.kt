package tech.stonks.drinkbar.data.drinkdetails.repository

import tech.stonks.drinkbar.data.drink.datasource.DrinkDataSource
import tech.stonks.drinkbar.data.drink.mapper.DrinkDataToDomainMapper
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinkdetails.repository.GetDrinkRepository

class GetDrinkRepositoryImpl(
    private val _drinkApiDataSource: DrinkDataSource,
    private val _drinkDataModelToDomainModelMapper: DrinkDataToDomainMapper
) : GetDrinkRepository {
    override fun drinkDetails(id: String): DrinkDomainModel {
        return _drinkDataModelToDomainModelMapper.map(_drinkApiDataSource.getDrink(id))
    }
}