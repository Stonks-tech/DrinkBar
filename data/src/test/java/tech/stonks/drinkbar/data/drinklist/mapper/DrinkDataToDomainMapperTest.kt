package tech.stonks.drinkbar.data.drinklist.mapper

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import tech.stonks.drinkbar.data.drink.model.DrinkDataModel
import tech.stonks.drinkbar.domain.drinklist.model.DrinkDomainModel

class DrinkDataToDomainMapperTest {
    private lateinit var _mapper: DrinkDataToDomainMapper

    @Before
    fun setUp() {
        _mapper = DrinkDataToDomainMapper()
    }

    @Test
    fun `given data model when map called then domain model returned`() {
        val dataModel = DrinkDataModel(
            id = "1",
            name = "name",
            description = "description",
            image = "image",
            thumbnail = "thumbnail",
        )

        val actual = _mapper.map(dataModel)

        val expected = DrinkDomainModel(
            id = "1",
            name = "name",
            description = "description",
            image = "image",
            thumbnail = "thumbnail",
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
        )

        val actual = _mapper.map(dataModel)

        val expected = DrinkDomainModel(
            id = "1",
            name = "name",
            description = "description",
            image = null,
            thumbnail = null,
        )
        assertEquals(expected, actual)
    }
}