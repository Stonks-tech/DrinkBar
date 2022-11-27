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
import tech.stonks.drinkbar.data.drinklist.repository.SearchDrinksRepositoryImpl
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinklist.repository.SearchDrinksRepository

class SearchDrinksRepositoryTest {
    private lateinit var _mapper: DrinkDataToDomainMapper
    private lateinit var _drinkDataSource: DrinkDataSource
    private lateinit var _repository: SearchDrinksRepository

    @Before
    fun setUp() {
        _mapper = mockk()
        _drinkDataSource = mockk()
        _repository = SearchDrinksRepositoryImpl(_drinkDataSource, _mapper)
    }

    @Test
    fun `when drinkList called then call drinkDataSource`() {
        every { _drinkDataSource.getDrinkList("a") } returns listOf(DRINK_DATA)
        every { _mapper.map(DRINK_DATA) } returns DRINK_DOMAIN

        val actual = _repository.searchDrinks("a")

        verify { _drinkDataSource.getDrinkList("a") }
    }

    @Test
    fun `when drinkList called then data mapped with mapper`() {
        every { _drinkDataSource.getDrinkList("a") } returns listOf(DRINK_DATA)
        every { _mapper.map(DRINK_DATA) } returns DRINK_DOMAIN

        val actual = _repository.searchDrinks("a")

        verify { _mapper.map(DRINK_DATA) }
    }

    @Test
    fun `when drinkList called then mapped data returned`() {
        every { _drinkDataSource.getDrinkList("a") } returns listOf(DRINK_DATA)
        every { _mapper.map(DRINK_DATA) } returns DRINK_DOMAIN

        val actual = _repository.searchDrinks("a")

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