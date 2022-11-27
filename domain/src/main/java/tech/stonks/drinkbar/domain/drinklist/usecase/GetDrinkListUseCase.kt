package tech.stonks.drinkbar.domain.drinklist.usecase

import tech.stonks.drinkbar.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import tech.stonks.drinkbar.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinklist.repository.DrinkListRepository

class GetDrinkListUseCase(
    private val _drinkListRepository: DrinkListRepository,
    coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<Unit, List<DrinkDomainModel>>(coroutineContextProvider) {
    override fun executeInBackground(request: Unit): List<DrinkDomainModel> {
        return _drinkListRepository.drinkList()
    }
}