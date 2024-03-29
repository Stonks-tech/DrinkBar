package tech.stonks.drinkbar.presentation.drinklist.model

import tech.stonks.drinkbar.presentation.drink.model.DrinkPresentationModel

data class DrinkListState(
    val drinks: List<DrinkPresentationModel> = emptyList(),
    val isCompact: Boolean = false,
    val isLoading: Boolean = false,
    val searchQuery: String = "",
) {
    fun loading(isLoading: Boolean = true) = copy(isLoading = isLoading)

    fun withSearchQuery(searchQuery: String) = copy(searchQuery = searchQuery)

    fun compact(isCompact: Boolean = true) = copy(isCompact = isCompact)

    fun withDrinks(drinks: List<DrinkPresentationModel>) = copy(
        drinks = drinks,
        isLoading = false
    )
}