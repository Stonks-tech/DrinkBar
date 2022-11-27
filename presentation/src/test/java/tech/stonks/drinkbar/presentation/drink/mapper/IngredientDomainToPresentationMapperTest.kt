package tech.stonks.drinkbar.presentation.drink.mapper

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import tech.stonks.drinkbar.domain.drink.model.IngredientDomainModel
import tech.stonks.drinkbar.presentation.drink.model.IngredientPresentationModel

class IngredientDomainToPresentationMapperTest {
    private lateinit var _mapper: IngredientDomainToPresentationMapper

    @Before
    fun setUp() {
        _mapper = IngredientDomainToPresentationMapper()
    }

    @Test
    fun `given domain model when map called then return presentation model`() {
        // given
        val domainModel = IngredientDomainModel(
            name = "name",
            measure = "measure",
        )

        // when
        val presentationModel = _mapper.map(domainModel)

        // then
        val expected = IngredientPresentationModel(
            name = "name",
            measure = "measure",
        )
        assertEquals(expected, presentationModel)
    }
}