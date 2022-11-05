package tech.stonks.drinkbar.datasource.drink.datasource

import retrofit2.HttpException
import tech.stonks.drinkbar.data.drink.datasource.DrinkDataSource
import tech.stonks.drinkbar.data.drink.model.DrinkDataModel
import tech.stonks.drinkbar.datasource.drink.mapper.DrinkApiDataSourceToDataModelMapper
import tech.stonks.drinkbar.datasource.drink.service.DrinkService

class DrinkLiveApiDataSource(
    private val _drinkService: DrinkService,
    private val _drinkApiDataSourceToDataModelMapper: DrinkApiDataSourceToDataModelMapper
) : DrinkDataSource {
    override fun getDrinkList(): List<DrinkDataModel> {
        val response = _drinkService.searchDrink("margarita")
            .execute()
        return if (response.isSuccessful) {
            response.body()!!
                .drinks
                .map {
                    _drinkApiDataSourceToDataModelMapper.map(it)
                }
        } else {
            throw HttpException(response)
        }
    }
}