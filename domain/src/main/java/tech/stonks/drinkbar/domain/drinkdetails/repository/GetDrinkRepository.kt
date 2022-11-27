package tech.stonks.drinkbar.domain.drinkdetails.repository

import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel

interface GetDrinkRepository {
    fun drinkDetails(id: String): DrinkDomainModel
}