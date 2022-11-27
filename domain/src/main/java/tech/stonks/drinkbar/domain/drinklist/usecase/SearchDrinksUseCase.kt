package tech.stonks.drinkbar.domain.drinklist.usecase

import tech.stonks.drinkbar.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import tech.stonks.drinkbar.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinklist.repository.SearchDrinksRepository

class SearchDrinksUseCase(
    private val _searchDrinksRepository: SearchDrinksRepository,
    coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<String, List<DrinkDomainModel>>(coroutineContextProvider) {
    override fun executeInBackground(request: String): List<DrinkDomainModel> {
        return _searchDrinksRepository.searchDrinks(request)
    }
}