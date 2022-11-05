package tech.stonks.drinkbar.datasource.drink.model

import com.squareup.moshi.Json

data class DrinkApiModel(
    @Json(name = "idDrink")
    val id: String,
    @Json(name = "strDrink")
    val name: String,
    @Json(name = "strInstructions")
    val description: String,
    @Json(name = "strDrinkThumb")
    val thumbnail: String?,
    @Json(name = "strImageSource")
    val image: String?
)