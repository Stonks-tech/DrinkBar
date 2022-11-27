package tech.stonks.drinkbar.presentation.drinkdetails.model

import tech.stonks.drinkbar.presentation.drink.model.DrinkPresentationModel

data class DrinkDetailsState(
    val drink: DrinkPresentationModel? = null,
    val isLoading: Boolean = false,
) {
    fun loading(isLoading: Boolean = true) = copy(isLoading = isLoading)
    fun withDrink(drink: DrinkPresentationModel) = copy(
        drink = drink,
        isLoading = false
    )
}