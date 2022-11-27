package tech.stonks.drinkbar.domain.cleanarchitecture.usecase

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import tech.stonks.drinkbar.domain.cleanarchitecture.exception.DomainException
import tech.stonks.drinkbar.domain.cleanarchitecture.exception.UnknownDomainException

@OptIn(FlowPreview::class)
class UseCaseExecutor(private val _coroutineScope: CoroutineScope) {
    private val _debounceState = MutableStateFlow(Unit)

    fun <OUTPUT> execute(
        useCase: UseCase<Unit, OUTPUT>,
        onSuccess: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) {
        execute(useCase, Unit, onSuccess, onException)
    }

    fun <INPUT, OUTPUT> execute(
        useCase: UseCase<INPUT, OUTPUT>,
        value: INPUT,
        onSuccess: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) {
        _coroutineScope.launch {
            try {
                useCase.execute(value, onSuccess)
            } catch (ignore: CancellationException) {
            } catch (throwable: Throwable) {
                onException(
                    (throwable as? DomainException)
                        ?: UnknownDomainException(throwable)
                )
            }
        }
    }

    fun <INPUT, OUTPUT> executeDebounced(
        useCase: UseCase<INPUT, OUTPUT>,
        value: INPUT,
        debounce: Long = 0,
        onSuccess: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) {
        _coroutineScope.launch {
            _debounceState.debounce(debounce)
                .collect {
                    try {
                        useCase.execute(value, onSuccess)
                    } catch (ignore: CancellationException) {
                    } catch (throwable: Throwable) {
                        onException(
                            (throwable as? DomainException)
                                ?: UnknownDomainException(throwable)
                        )
                    }
                }

        }
    }
}