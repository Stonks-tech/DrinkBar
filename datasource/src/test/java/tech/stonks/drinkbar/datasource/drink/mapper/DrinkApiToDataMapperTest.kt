package tech.stonks.drinkbar.datasource.drink.mapper

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import tech.stonks.drinkbar.data.drink.model.DrinkDataModel
import tech.stonks.drinkbar.data.drink.model.IngredientDataModel
import tech.stonks.drinkbar.datasource.drink.model.DrinkApiModel

class DrinkApiToDataMapperTest {
    private lateinit var _ingredientMapper: IngredientStringsToDataMapper
    private lateinit var _mapper: DrinkApiToDataMapper

    @Before
    fun setUp() {
        _ingredientMapper = mockk()
        _mapper = DrinkApiToDataMapper(_ingredientMapper)
    }

    @Test
    fun `given drinkApiModel when map called then drinkDataModel returned`() {
        every { _ingredientMapper.map("Tequila" to "1 1/2 oz ") } returns IngredientDataModel(
            "Tequila",
            "1 1/2 oz "
        )

        val actual = _mapper.map(DRINK_API)

        assertEquals(DRINK_DATA, actual)
    }

    @Test
    fun `given drinkApiModel when map called then ingredient should be mapped with ingredient mapper`() {
        every { _ingredientMapper.map("Tequila" to "1 1/2 oz ") } returns IngredientDataModel(
            "Tequila",
            "1 1/2 oz "
        )

        _mapper.map(DRINK_API)

        verify { _ingredientMapper.map("Tequila" to "1 1/2 oz ") }
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
            )
        )

        private val DRINK_API = DrinkApiModel(
            id = "11007",
            name = "Margarita",
            description = "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten only the outer rim and sprinkle the salt on it. The salt should present to the lips of the imbiber and never mix into the cocktail. Shake the other ingredients with ice, then carefully pour into the glass.",
            thumbnail = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg",
            image = "https://commons.wikimedia.org/wiki/File:Klassiche_Margarita.jpg",
            ingredient1 = "Tequila",
            measure1 = "1 1/2 oz ",
        )
    }
}