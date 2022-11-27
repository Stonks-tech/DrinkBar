package tech.stonks.drinkbar.presentation.architecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import tech.stonks.drinkbar.domain.cleanarchitecture.exception.DomainException
import tech.stonks.drinkbar.domain.cleanarchitecture.usecase.UseCase
import tech.stonks.drinkbar.presentation.architecture.model.PresentationDestination
import tech.stonks.drinkbar.presentation.architecture.viewmodel.livedata.SingleLiveEvent
import tech.stonks.drinkbar.presentation.architecture.viewmodel.usecase.UseCaseExecutorProvider

abstract class BaseViewModel<STATE : Any, NOTIFICATION : Any>(
    useCaseExecutorProvider: UseCaseExecutorProvider
) : ViewModel() {
    private val _state = MutableLiveData<STATE>().apply { value = initialState() }
    val state: LiveData<STATE> = _state

    private val _notification: SingleLiveEvent<NOTIFICATION> = SingleLiveEvent()
    val notification: LiveData<NOTIFICATION> = _notification

    private val _destination: SingleLiveEvent<PresentationDestination> = SingleLiveEvent()
    val destination: LiveData<PresentationDestination> = _destination

    protected abstract fun initialState(): STATE

    private val currentState: STATE
        get() = state.value ?: initialState()

    private val useCaseExecutor by lazy {
        useCaseExecutorProvider(viewModelScope)
    }

    protected fun <OUTPUT> execute(
        useCase: UseCase<Unit, OUTPUT>,
        onSuccess: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) {
        execute(useCase, Unit, onSuccess, onException)
    }

    protected fun <INPUT, OUTPUT> execute(
        useCase: UseCase<INPUT, OUTPUT>,
        value: INPUT,
        onSuccess: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) {
        useCaseExecutor.execute(useCase, value, onSuccess, onException)
    }

    protected fun <INPUT, OUTPUT> executeDebounced(
        useCase: UseCase<INPUT, OUTPUT>,
        value: INPUT,
        debounce: Long = 300,
        onSuccess: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) {
        useCaseExecutor.executeDebounced(useCase, value, debounce, onSuccess, onException)
    }

    protected fun updateState(newState: STATE) {
        _state.value = newState
    }

    protected fun updateState(
        updatedState: STATE.() -> STATE
    ) = updateState(currentState.updatedState())

    protected fun notify(notification: NOTIFICATION) {
        _notification.value = notification
    }

    protected fun navigateTo(destination: PresentationDestination) {
        _destination.value = destination
    }

    protected fun navigateBack() {
        _destination.value = PresentationDestination.Back
    }
}