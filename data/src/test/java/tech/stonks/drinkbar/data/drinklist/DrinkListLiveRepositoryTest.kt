package tech.stonks.drinkbar.data.drinklist

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import tech.stonks.drinkbar.data.drink.datasource.DrinkDataSource
import tech.stonks.drinkbar.data.drink.mapper.DrinkDataToDomainMapper
import tech.stonks.drinkbar.data.drink.model.DrinkDataModel
import tech.stonks.drinkbar.data.drinklist.repository.DrinkListRepositoryImpl
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel

class DrinkListLiveRepositoryTest {
    private lateinit var _mapper: DrinkDataToDomainMapper
    private lateinit var _drinkDataSource: DrinkDataSource
    private lateinit var _repository: DrinkListRepositoryImpl

    @Before
    fun setUp() {
        _mapper = mockk()
        _drinkDataSource = mockk()
        _repository = DrinkListRepositoryImpl(_drinkDataSource, _mapper)
    }

    @Test
    fun `when drinkList called then call drinkDataSource`() {
        every { _drinkDataSource.getDrinkList() } returns listOf(DRINK_DATA)
        every { _mapper.map(DRINK_DATA) } returns DRINK_DOMAIN

        val actual = _repository.drinkList()

        verify { _drinkDataSource.getDrinkList() }
    }

    @Test
    fun `when drinkList called then data mapped with mapper`() {
        every { _drinkDataSource.getDrinkList() } returns listOf(DRINK_DATA)
        every { _mapper.map(DRINK_DATA) } returns DRINK_DOMAIN

        val actual = _repository.drinkList()

        verify { _mapper.map(DRINK_DATA) }
    }

    @Test
    fun `when drinkList called then mapped data returned`() {
        every { _drinkDataSource.getDrinkList() } returns listOf(DRINK_DATA)
        every { _mapper.map(DRINK_DATA) } returns DRINK_DOMAIN

        val actual = _repository.drinkList()

        assertEquals(listOf(DRINK_DOMAIN), actual)
    }

    companion object {
        private val DRINK_DATA = DrinkDataModel(
            id = "1",
            name = "name",
            description = "description",
            image = "image",
            thumbnail = "thumbnail",
            ingredients = emptyList(),
        )
        private val DRINK_DOMAIN = DrinkDomainModel(
            id = "1",
            name = "name",
            description = "description",
            image = "image",
            thumbnail = "thumbnail",
            ingredients = emptyList(),
        )
    }
}