package tech.stonks.drinkbar.data.drinkdetails

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import tech.stonks.drinkbar.data.drink.datasource.DrinkDataSource
import tech.stonks.drinkbar.data.drink.mapper.DrinkDataToDomainMapper
import tech.stonks.drinkbar.data.drink.model.DrinkDataModel
import tech.stonks.drinkbar.data.drinkdetails.repository.GetDrinkRepositoryImpl
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinkdetails.repository.GetDrinkRepository

class GetDrinkRepositoryTest {
    private lateinit var _mapper: DrinkDataToDomainMapper
    private lateinit var _drinkDataSource: DrinkDataSource
    private lateinit var _repository: GetDrinkRepository

    @Before
    fun setup() {
        _mapper = mockk()
        _drinkDataSource = mockk()
        _repository = GetDrinkRepositoryImpl(_drinkDataSource, _mapper)
    }

    @Test
    fun `given drink id when drinkDetails called then call drinkDataSource`() {
        every { _drinkDataSource.getDrink(DRINK_ID) } returns DRINK_DATA
        every { _mapper.map(DRINK_DATA) } returns DRINK_DOMAIN

        _repository.drinkDetails(DRINK_ID)

        verify { _drinkDataSource.getDrink(DRINK_ID) }
    }

    @Test
    fun `given drink id when drinkDetails called then data mapped with mapper`() {
        every { _drinkDataSource.getDrink(DRINK_ID) } returns DRINK_DATA
        every { _mapper.map(DRINK_DATA) } returns DRINK_DOMAIN

        _repository.drinkDetails(DRINK_ID)

        verify { _mapper.map(DRINK_DATA) }
    }

    @Test
    fun `given drink id when drinkDetails called then mapped data returned`() {
        every { _drinkDataSource.getDrink(DRINK_ID) } returns DRINK_DATA
        every { _mapper.map(DRINK_DATA) } returns DRINK_DOMAIN

        val actual = _repository.drinkDetails(DRINK_ID)

        assertEquals(DRINK_DOMAIN, actual)
    }

    companion object {
        private const val DRINK_ID = "1"
        private val DRINK_DATA = DrinkDataModel(
            id = "1",
            name = "name",
            image = "image",
            thumbnail = "thumbnail",
            description = "description",
            ingredients = emptyList()
        )
        private val DRINK_DOMAIN = DrinkDomainModel(
            id = "1",
            name = "name",
            image = "image",
            thumbnail = "thumbnail",
            description = "description",
            ingredients = emptyList()
        )
    }
}