package tech.stonks.drinkbar.presentation.drinklist.model

data class DrinkPresentationModel(
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: String?,
)