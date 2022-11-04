package tech.stonks.drinkbar.domain.drinklist.repository

import tech.stonks.drinkbar.domain.drinklist.model.DrinkDomainModel

interface DrinkListRepository {
    fun drinkList(): List<DrinkDomainModel>
}