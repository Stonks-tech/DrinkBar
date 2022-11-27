package tech.stonks.drinkbar.data.drinklist.repository

import tech.stonks.drinkbar.data.drink.datasource.DrinkDataSource
import tech.stonks.drinkbar.data.drink.mapper.DrinkDataToDomainMapper
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinklist.repository.SearchDrinksRepository

class SearchDrinksRepositoryImpl(
    private val _drinkDataSource: DrinkDataSource,
    private val _drinkDataModelToDomainModelMapper: DrinkDataToDomainMapper
) : SearchDrinksRepository {
    override fun searchDrinks(query: String): List<DrinkDomainModel> {
        return _drinkDataSource.getDrinkList(query).map(_drinkDataModelToDomainModelMapper::map)
    }
}