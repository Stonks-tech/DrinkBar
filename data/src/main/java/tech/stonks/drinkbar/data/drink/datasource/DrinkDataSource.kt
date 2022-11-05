package tech.stonks.drinkbar.data.drink.datasource

import tech.stonks.drinkbar.data.drink.model.DrinkDataModel

interface DrinkDataSource {
    fun getDrinkList(): List<DrinkDataModel>
}