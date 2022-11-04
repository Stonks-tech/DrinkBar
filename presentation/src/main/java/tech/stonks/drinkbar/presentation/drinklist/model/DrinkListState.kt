package tech.stonks.drinkbar.presentation.drinklist.model

data class DrinkListState(
    val drinks: List<DrinkPresentationModel> = emptyList(),
    val isLoading: Boolean = false,
) {
    fun loading() = copy(isLoading = true)

    fun withDrinks(drinks: List<DrinkPresentationModel>) = copy(
        drinks = drinks,
        isLoading = false
    )
}