package tech.stonks.drinkbar.data.drink.mapper

import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import tech.stonks.drinkbar.data.drink.model.DrinkDataModel
import tech.stonks.drinkbar.data.drink.model.IngredientDataModel
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drink.model.IngredientDomainModel

class DrinkDataToDomainMapperTest {
    private lateinit var _ingredientMapper: IngredientDataToDomainMapper
    private lateinit var _mapper: DrinkDataToDomainMapper

    @Before
    fun setUp() {
        _ingredientMapper = mockk {
            every { map(INGREDIENT_DATA_MODEL) } returns INGREDIENT_DOMAIN_MODEL
        }
        _mapper = DrinkDataToDomainMapper(_ingredientMapper)
    }

    @Test
    fun `given data model when map called then domain model returned`() {
        val dataModel = DrinkDataModel(
            id = "1",
            name = "name",
            description = "description",
            image = "image",
            thumbnail = "thumbnail",
            ingredients = listOf(INGREDIENT_DATA_MODEL),
        )

        val actual = _mapper.map(dataModel)

        val expected = DrinkDomainModel(
            id = "1",
            name = "name",
            description = "description",
            image = "image",
            thumbnail = "thumbnail",
            ingredients = listOf(INGREDIENT_DOMAIN_MODEL),
        )
        assertEquals(expected, actual)
    }

    @Test
    fun `given data model with null images when map called then domain model returned without images`() {
        val dataModel = DrinkDataModel(
            id = "1",
            name = "name",
            description = "description",
            image = null,
            thumbnail = null,
            ingredients = listOf(INGREDIENT_DATA_MODEL),
        )

        val actual = _mapper.map(dataModel)

        val expected = DrinkDomainModel(
            id = "1",
            name = "name",
            description = "description",
            image = null,
            thumbnail = null,
            ingredients = listOf(INGREDIENT_DOMAIN_MODEL),
        )
        assertEquals(expected, actual)
    }

    companion object {
        private val INGREDIENT_DATA_MODEL = IngredientDataModel(
            name = "name",
            measure = "measure",
        )

        private val INGREDIENT_DOMAIN_MODEL = IngredientDomainModel(
            name = "name",
            measure = "measure",
        )
    }
}