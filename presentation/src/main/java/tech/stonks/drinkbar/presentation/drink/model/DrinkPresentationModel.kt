package tech.stonks.drinkbar.presentation.drink.model

data class DrinkPresentationModel(
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: String?,
)