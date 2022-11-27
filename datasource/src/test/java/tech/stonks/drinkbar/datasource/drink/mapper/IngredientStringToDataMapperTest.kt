package tech.stonks.drinkbar.datasource.drink.mapper

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import tech.stonks.drinkbar.data.drink.model.IngredientDataModel

class IngredientStringToDataMapperTest {
    private lateinit var _mapper: IngredientStringsToDataMapper

    @Before
    fun setUp() {
        _mapper = IngredientStringsToDataMapper()
    }

    @Test
    fun `given strings when map called then return data model`() {
        val result = _mapper.map("name1" to "measure1")

        val expected = IngredientDataModel("name1", "measure1")
        assertEquals(expected, result)
    }

    @Test
    fun `given strings with null when map called then return data model with null`() {
        val result = _mapper.map("name1" to null)

        val expected = IngredientDataModel("name1", null)
        assertEquals(expected, result)
    }
}