package tech.stonks.drinkbar.datasource.drink.datasource

import retrofit2.HttpException
import tech.stonks.drinkbar.data.drink.datasource.DrinkDataSource
import tech.stonks.drinkbar.data.drink.model.DrinkDataModel
import tech.stonks.drinkbar.datasource.drink.mapper.DrinkApiToDataMapper
import tech.stonks.drinkbar.datasource.drink.service.DrinkService

class DrinkLiveDataSource(
    private val _drinkService: DrinkService,
    private val _drinkApiToDataMapper: DrinkApiToDataMapper
) : DrinkDataSource {
    override fun getDrinkList(): List<DrinkDataModel> {
        val response = _drinkService.searchDrink("")
            .execute()
        return if (response.isSuccessful) {
            response.body()!!
                .drinks
                .map {
                    _drinkApiToDataMapper.map(it)
                }
        } else {
            throw HttpException(response)
        }
    }
}