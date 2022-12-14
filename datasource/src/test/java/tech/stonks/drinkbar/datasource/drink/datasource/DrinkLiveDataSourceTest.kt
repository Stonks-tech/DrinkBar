package tech.stonks.drinkbar.datasource.drink.datasource

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.concurrent.TimeUnit
import junit.framework.Assert.assertEquals
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.stonks.drinkbar.data.drink.datasource.DrinkDataSource
import tech.stonks.drinkbar.data.drink.model.DrinkDataModel
import tech.stonks.drinkbar.data.drink.model.IngredientDataModel
import tech.stonks.drinkbar.datasource.drink.mapper.DrinkApiToDataMapper
import tech.stonks.drinkbar.datasource.drink.model.DrinkApiModel
import tech.stonks.drinkbar.datasource.drink.service.DrinkService
import tech.stonks.drinkbar.datasource.enqueueResponse

class DrinkLiveDataSourceTest {
    private val _mockWebServer = MockWebServer()
    private val _client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val _drinkService: DrinkService = Retrofit.Builder()
        .baseUrl(_mockWebServer.url("/"))
        .client(_client)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .build()
        .create(DrinkService::class.java)
    private lateinit var _drinkApiToDataMapper: DrinkApiToDataMapper
    private lateinit var _dataSource: DrinkDataSource

    @Before
    fun setUp() {
        _drinkApiToDataMapper = mockk()
        _dataSource = DrinkLiveDataSource(_drinkService, _drinkApiToDataMapper)
    }

    @After
    fun tearDown() {
        _mockWebServer.shutdown()
    }

    @Test
    fun `given nothing when searchDrink then return drink list`() {
        _mockWebServer.enqueueResponse("search-drink-200.json", 200)
        every { _drinkApiToDataMapper.map(DRINK_API) } returns DRINK_DATA

        val actual = _dataSource.getDrinkList("")

        assertEquals(listOf(DRINK_DATA), actual)
    }

    @Test
    fun `given nothing when searchDrink then map with mapper`() {
        _mockWebServer.enqueueResponse("search-drink-200.json", 200)
        every { _drinkApiToDataMapper.map(DRINK_API) } returns DRINK_DATA

        val actual = _dataSource.getDrinkList("")

        verify { _drinkApiToDataMapper.map(DRINK_API) }
    }

    @Test
    fun `given drinkId when getDrink then return drink`() {
        _mockWebServer.enqueueResponse("get-drink-200.json", 200)
        every { _drinkApiToDataMapper.map(DRINK_API) } returns DRINK_DATA

        val actual = _dataSource.getDrink("1")

        assertEquals(DRINK_DATA, actual)
    }

    @Test
    fun `given drinkId when getDrink then map with mapper`() {
        _mockWebServer.enqueueResponse("get-drink-200.json", 200)
        every { _drinkApiToDataMapper.map(DRINK_API) } returns DRINK_DATA

        val actual = _dataSource.getDrink("1")

        verify { _drinkApiToDataMapper.map(DRINK_API) }
    }

    companion object {
        private val DRINK_DATA = DrinkDataModel(
            id = "11007",
            name = "Margarita",
            image = "https://commons.wikimedia.org/wiki/File:Klassiche_Margarita.jpg",
            description = "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten only the outer rim and sprinkle the salt on it. The salt should present to the lips of the imbiber and never mix into the cocktail. Shake the other ingredients with ice, then carefully pour into the glass.",
            thumbnail = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg",
            ingredients = listOf(
                IngredientDataModel("Tequila", "1 1/2 oz "),
                IngredientDataModel("Triple sec", "1/2 oz "),
                IngredientDataModel("Lime juice", "1 oz "),
                IngredientDataModel("Salt", null)
            )
        )

        private val DRINK_API = DrinkApiModel(
            id = "11007",
            name = "Margarita",
            description = "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten only the outer rim and sprinkle the salt on it. The salt should present to the lips of the imbiber and never mix into the cocktail. Shake the other ingredients with ice, then carefully pour into the glass.",
            thumbnail = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg",
            image = "https://commons.wikimedia.org/wiki/File:Klassiche_Margarita.jpg",
            ingredient1 = "Tequila",
            ingredient2 = "Triple sec",
            ingredient3 = "Lime juice",
            ingredient4 = "Salt",
            measure1 = "1 1/2 oz ",
            measure2 = "1/2 oz ",
            measure3 = "1 oz ",
        )
    }
}