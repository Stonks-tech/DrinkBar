package tech.stonks.drinkbar.data.drink.datasource

import tech.stonks.drinkbar.data.drink.model.DrinkDataModel

interface DrinkDataSource {
    fun getDrinkList(query: String): List<DrinkDataModel>
    fun getDrink(id: String): DrinkDataModel
}