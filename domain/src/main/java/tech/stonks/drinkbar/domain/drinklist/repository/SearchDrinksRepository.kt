package tech.stonks.drinkbar.domain.drinklist.repository

import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel

interface SearchDrinksRepository {
    fun searchDrinks(query: String): List<DrinkDomainModel>
}