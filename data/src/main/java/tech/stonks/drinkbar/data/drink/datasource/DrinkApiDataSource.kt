package tech.stonks.drinkbar.data.drink.datasource

import tech.stonks.drinkbar.data.drink.model.DrinkDataModel

interface DrinkApiDataSource {
    fun getDrinkList(): List<DrinkDataModel>
}