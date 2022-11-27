package tech.stonks.drinkbar.domain.drinkdetails.usecase

import tech.stonks.drinkbar.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import tech.stonks.drinkbar.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinkdetails.repository.GetDrinkRepository

class GetDrinkDetailsUseCase(
    private val _drinkRepository: GetDrinkRepository,
    coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<String, DrinkDomainModel>(coroutineContextProvider) {
    override fun executeInBackground(request: String): DrinkDomainModel {
        return _drinkRepository.drinkDetails(request)
    }
}