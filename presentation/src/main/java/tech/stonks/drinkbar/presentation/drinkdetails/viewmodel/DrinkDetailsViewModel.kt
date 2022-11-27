package tech.stonks.drinkbar.presentation.drinkdetails.viewmodel

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import tech.stonks.drinkbar.domain.drinkdetails.usecase.GetDrinkDetailsUseCase
import tech.stonks.drinkbar.presentation.architecture.viewmodel.BaseViewModel
import tech.stonks.drinkbar.presentation.architecture.viewmodel.usecase.UseCaseExecutorProvider
import tech.stonks.drinkbar.presentation.drink.mapper.DrinkDomainToPresentationMapper
import tech.stonks.drinkbar.presentation.drinkdetails.model.DrinkDetailsState

class DrinkDetailsViewModel @AssistedInject constructor(
    @Assisted private val _drinkId: String,
    private val _getDrinkDetailsUseCase: GetDrinkDetailsUseCase,
    private val _drinkDomainToPresentationMapper: DrinkDomainToPresentationMapper,
    useCaseExecutor: UseCaseExecutorProvider
) : BaseViewModel<DrinkDetailsState, Unit>(useCaseExecutor) {

    override fun initialState(): DrinkDetailsState {
        return DrinkDetailsState()
    }

    fun onEntered() {
        loadDrink()
    }

    private fun loadDrink() {
        updateState(DrinkDetailsState::loading)
        execute(
            _getDrinkDetailsUseCase,
            _drinkId,
            onSuccess = {
                updateState { withDrink(it.let(_drinkDomainToPresentationMapper::map)) }
            },
            onException = {
                it.printStackTrace()
                updateState { loading(false) }
            }
        )
    }
}