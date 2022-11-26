package tech.stonks.drinkbar.domain.drinklist.repository

import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel

interface DrinkListRepository {
    fun drinkList(): List<DrinkDomainModel>
}