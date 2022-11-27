package tech.stonks.drinkbar.presentation.drink.mapper

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drink.model.IngredientDomainModel
import tech.stonks.drinkbar.presentation.drink.model.DrinkPresentationModel
import tech.stonks.drinkbar.presentation.drink.model.IngredientPresentationModel

class DrinkDomainToPresentationMapperTest {
    private lateinit var _ingredientsMapper: IngredientDomainToPresentationMapperTest
    private lateinit var _mapper: DrinkDomainToPresentationMapper

    @Before
    fun setUp() {
        _ingredientsMapper = mockk {
            every { map(INDEGRIENT_DOMAIN) } returns INGREDIENT_PRESENTATION
        }
        _mapper = DrinkDomainToPresentationMapper(_ingredientsMapper)
    }

    @Test
    fun `given domain model when map called then return presentation model`() {
        // given
        val domainModel = DrinkDomainModel(
            id = "1",
            name = "name",
            description = "description",
            thumbnail = "thumbnail",
            image = "image",
            ingredients = listOf(INDEGRIENT_DOMAIN),
        )

        // when
        val presentationModel = _mapper.map(domainModel)

        // then
        val expected = DrinkPresentationModel(
            id = "1",
            name = "name",
            description = "description",
            thumbnail = "thumbnail",
            ingredients = listOf(INGREDIENT_PRESENTATION),
        )
        assertEquals(expected, presentationModel)
    }

    @Test
    fun `given domain model when map called then map indegrient with mapper`() {
        // given
        val domainModel = DrinkDomainModel(
            id = "1",
            name = "name",
            description = "description",
            thumbnail = "thumbnail",
            image = "image",
            ingredients = listOf(INDEGRIENT_DOMAIN),
        )

        // when
        val presentationModel = _mapper.map(domainModel)

        // then
        verify { _ingredientsMapper.map(INDEGRIENT_DOMAIN) }
    }

    companion object {
        private val INDEGRIENT_DOMAIN = IngredientDomainModel(
            name = "name",
            measure = "measure",
        )

        private val INGREDIENT_PRESENTATION = IngredientPresentationModel(
            name = "name",
            measure = "measure",
        )
    }
}