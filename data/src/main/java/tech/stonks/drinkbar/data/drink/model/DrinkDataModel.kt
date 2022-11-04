package tech.stonks.drinkbar.data.drink.model

data class DrinkDataModel(
    val id: String,
    val name: String,
    val description: String,
    val image: String?,
    val thumbnail: String?,
)