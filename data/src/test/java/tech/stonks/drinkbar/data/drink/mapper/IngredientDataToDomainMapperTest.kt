package tech.stonks.drinkbar.data.drink.mapper

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import tech.stonks.drinkbar.data.drink.model.IngredientDataModel
import tech.stonks.drinkbar.domain.drink.model.IngredientDomainModel

class IngredientDataToDomainMapperTest {
    private lateinit var _mapper: IngredientDataToDomainMapper

    @Before
    fun setUp() {
        _mapper = IngredientDataToDomainMapper()
    }

    @Test
    fun `given data model when map called then domain model returned`() {
        val dataModel = IngredientDataModel(
            name = "name",
            measure = "measure",
        )

        val actual = _mapper.map(dataModel)

        val expected = IngredientDomainModel(
            name = "name",
            measure = "measure",
        )
        assertEquals(expected, actual)
    }
}