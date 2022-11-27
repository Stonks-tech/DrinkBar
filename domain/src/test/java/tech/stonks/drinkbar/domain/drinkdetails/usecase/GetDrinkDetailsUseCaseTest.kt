package tech.stonks.drinkbar.domain.drinkdetails.usecase

import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import tech.stonks.drinkbar.domain.FakeCoroutineContextProvider
import tech.stonks.drinkbar.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinkdetails.repository.GetDrinkRepository

class GetDrinkDetailsUseCaseTest {
    private lateinit var _drinkDetailsRepository: GetDrinkRepository
    private lateinit var _coroutineContextProvider: CoroutineContextProvider
    private lateinit var _useCase: GetDrinkDetailsUseCase

    @Before
    fun setup() {
        _drinkDetailsRepository = mockk()
        _coroutineContextProvider = FakeCoroutineContextProvider
        _useCase = GetDrinkDetailsUseCase(_drinkDetailsRepository, _coroutineContextProvider)
    }

    @Test
    fun `given drinkId when executeInBackground then call drinkDetails`() {
        coEvery { _drinkDetailsRepository.drinkDetails(any()) } returns DRINK_DOMAIN

        _useCase.executeInBackground("1")

        coEvery { _drinkDetailsRepository.drinkDetails(any()) }
    }

    companion object {
        private val DRINK_DOMAIN = DrinkDomainModel(
            "1",
            "Drink",
            "description",
            "thumbnail",
            "image",
            emptyList()
        )
    }
}