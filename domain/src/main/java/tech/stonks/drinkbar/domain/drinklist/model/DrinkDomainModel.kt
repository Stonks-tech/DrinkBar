package tech.stonks.drinkbar.domain.drinklist.model

data class DrinkDomainModel(
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: String?,
    val image: String?
)