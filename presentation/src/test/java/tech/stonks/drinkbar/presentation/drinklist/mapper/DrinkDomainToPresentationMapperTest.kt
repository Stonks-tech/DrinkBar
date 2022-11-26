package tech.stonks.drinkbar.presentation.drinklist.mapper

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.presentation.drink.mapper.DrinkDomainToPresentationMapper
import tech.stonks.drinkbar.presentation.drink.model.DrinkPresentationModel

class DrinkDomainToPresentationMapperTest {
    private lateinit var _mapper: DrinkDomainToPresentationMapper

    @Before
    fun setUp() {
        _mapper = DrinkDomainToPresentationMapper()
    }

    @Test
    fun `given domain model when map called then return presentation model`() {
        // given
        val domainModel = DrinkDomainModel(
            id = "1",
            name = "name",
            description = "description",
            thumbnail = "thumbnail",
            image = "image"
        )

        // when
        val presentationModel = _mapper.map(domainModel)

        // then
        val expected = DrinkPresentationModel(
            id = "1",
            name = "name",
            description = "description",
            thumbnail = "thumbnail",
        )
        assertEquals(expected, presentationModel)
    }
}