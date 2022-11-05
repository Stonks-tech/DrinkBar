package tech.stonks.drinkbar.presentation.drinklist.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import tech.stonks.drinkbar.domain.drinklist.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinklist.usecase.GetDrinkListUseCase
import tech.stonks.drinkbar.presentation.architecture.viewmodel.BaseViewModel
import tech.stonks.drinkbar.presentation.architecture.viewmodel.usecase.UseCaseExecutorProvider
import tech.stonks.drinkbar.presentation.drinklist.mapper.DrinkDomainToPresentationMapper
import tech.stonks.drinkbar.presentation.drinklist.model.DrinkListState
import javax.inject.Inject

@HiltViewModel
class DrinkListViewModel @Inject constructor(
    private val _getDrinkListUseCase: GetDrinkListUseCase,
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

    private fun loadDrinks() {
        updateState(DrinkListState::loading)
        execute(
            _getDrinkListUseCase,
            Unit,
            onSuccess = { drinks -> presentDrinks(drinks) },
            onException = {
                it.printStackTrace()
                Unit }
        )
    }

    private fun presentDrinks(drinks: Collection<DrinkDomainModel>) {
        updateState {
            withDrinks(drinks.map(_drinkDomainToPresentationMapper::map))
        }
    }
}