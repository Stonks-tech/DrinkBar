package tech.stonks.drinkbar.presentation.drinklist.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinklist.usecase.GetDrinkListUseCase
import tech.stonks.drinkbar.domain.drinklist.usecase.SearchDrinksUseCase
import tech.stonks.drinkbar.presentation.architecture.viewmodel.BaseViewModel
import tech.stonks.drinkbar.presentation.architecture.viewmodel.usecase.UseCaseExecutorProvider
import tech.stonks.drinkbar.presentation.drink.mapper.DrinkDomainToPresentationMapper
import tech.stonks.drinkbar.presentation.drinklist.model.DrinkListPresentationDestination
import tech.stonks.drinkbar.presentation.drinklist.model.DrinkListState
import javax.inject.Inject

@HiltViewModel
class DrinkListViewModel @Inject constructor(
    private val _getDrinkListUseCase: GetDrinkListUseCase,
    private val _searchDrinksUseCase: SearchDrinksUseCase,
    private val _drinkDomainToPresentationMapper: DrinkDomainToPresentationMapper,
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<DrinkListState, Unit>(useCaseExecutorProvider) {
    override fun initialState() = DrinkListState()

    fun onEntered() {
        loadDrinks()
    }

    fun onRefreshAction() {
        loadDrinks()
    }

    fun onSearchAction(query: String) {
        updateState { withSearchQuery(query) }
        if (state.value?.isLoading == false) {
            updateState(DrinkListState::loading)
            execute(
                _searchDrinksUseCase,
                query,
                onSuccess = { drinks -> presentDrinks(drinks) },
                onException = {
                    it.printStackTrace()
                    updateState { loading(false) }
                }
            )
        }
    }

    fun onItemClicked(drinkId: String) {
        navigateTo(DrinkListPresentationDestination.DrinkDetails(drinkId))
    }

    private fun loadDrinks() {
        updateState(DrinkListState::loading)
        execute(
            _getDrinkListUseCase,
            Unit,
            onSuccess = { drinks -> presentDrinks(drinks) },
            onException = {
                it.printStackTrace()
                updateState { loading(false) }
            }
        )
    }

    private fun presentDrinks(drinks: Collection<DrinkDomainModel>) {
        updateState {
            withDrinks(drinks.map(_drinkDomainToPresentationMapper::map))
        }
    }
}