package tech.stonks.drinkbar.datasource.drink.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tech.stonks.drinkbar.datasource.drink.model.SearchDrinksResponseApiModel

interface DrinkService {
    companion object {
        const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    }

    @GET("search.php")
    fun searchDrink(@Query("s") drinkName: String = ""): Call<SearchDrinksResponseApiModel>

    @GET("lookup.php")
    fun getDrink(@Query("i") drinkId: String): Call<SearchDrinksResponseApiModel>
}