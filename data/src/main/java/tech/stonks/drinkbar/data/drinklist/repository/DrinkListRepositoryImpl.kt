package tech.stonks.drinkbar.data.drinklist.repository

import tech.stonks.drinkbar.data.drink.datasource.DrinkDataSource
import tech.stonks.drinkbar.data.drink.mapper.DrinkDataToDomainMapper
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinklist.repository.DrinkListRepository

class DrinkListRepositoryImpl(
    private val _drinkApiDataSource: DrinkDataSource,
    private val _drinkDataModelToDomainModelMapper: DrinkDataToDomainMapper
) : DrinkListRepository {
    override fun drinkList(): List<DrinkDomainModel> {
        return _drinkApiDataSource.getDrinkList("")
            .map { _drinkDataModelToDomainModelMapper.map(it) }
    }
}