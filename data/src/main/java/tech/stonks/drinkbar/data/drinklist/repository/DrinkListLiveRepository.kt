package tech.stonks.drinkbar.data.drinklist.repository

import tech.stonks.drinkbar.data.drink.datasource.DrinkApiDataSource
import tech.stonks.drinkbar.data.drinklist.mapper.DrinkDataModelToDomainModelMapper
import tech.stonks.drinkbar.domain.drinklist.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinklist.repository.DrinkListRepository

class DrinkListLiveRepository(
    private val _drinkApiDataSource: DrinkApiDataSource,
    private val _drinkDataModelToDomainModelMapper: DrinkDataModelToDomainModelMapper
) : DrinkListRepository {
    override fun drinkList(): List<DrinkDomainModel> {
        return _drinkApiDataSource.getDrinkList().map { _drinkDataModelToDomainModelMapper.map(it) }
    }
}