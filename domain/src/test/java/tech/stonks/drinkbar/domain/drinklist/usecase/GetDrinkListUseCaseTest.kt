@file:OptIn(ExperimentalCoroutinesApi::class)

package tech.stonks.drinkbar.domain.drinklist.usecase

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import tech.stonks.drinkbar.domain.FakeCoroutineContextProvider
import tech.stonks.drinkbar.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinklist.repository.DrinkListRepository

class GetDrinkListUseCaseTest {
    private lateinit var _drinkListRepository: DrinkListRepository
    private lateinit var _coroutineContextProvider: CoroutineContextProvider
    private lateinit var _useCase: GetDrinkListUseCase


    @Before
    fun setUp() {
        _drinkListRepository = mockk()
        _coroutineContextProvider = FakeCoroutineContextProvider
        _useCase = GetDrinkListUseCase(_drinkListRepository, _coroutineContextProvider)
    }

    @Test
    fun `given Unit when executeInBackground then call drinkList`() = runTest {
        every { _drinkListRepository.drinkList() } returns listOf(DRINK_DOMAIN)

        val actual = _useCase.executeInBackground(Unit)

        verify { _drinkListRepository.drinkList() }
    }

    @Test
    fun `given Unit when executeInBackground then return drinkList`() = runTest {
        every { _drinkListRepository.drinkList() } returns listOf(DRINK_DOMAIN)

        val actual = _useCase.executeInBackground(Unit)

        assertEquals(listOf(DRINK_DOMAIN), actual)
    }


    companion object {
        private val DRINK_DOMAIN = DrinkDomainModel(
            id = "1",
            name = "name",
            description = "description",
            image = "image",
            thumbnail = "thumbnail"
        )
    }
}