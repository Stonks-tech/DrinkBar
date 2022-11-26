package tech.stonks.drinkbar.presentation.drinklist.model

import tech.stonks.drinkbar.presentation.drink.model.DrinkPresentationModel

data class DrinkListState(
    val drinks: List<DrinkPresentationModel> = emptyList(),
    val isLoading: Boolean = false,
) {
    fun loading(isLoading: Boolean = true) = copy(isLoading = isLoading)

    fun withDrinks(drinks: List<DrinkPresentationModel>) = copy(
        drinks = drinks,
        isLoading = false
    )
}