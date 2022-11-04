package tech.stonks.drinkbar.domain.cleanarchitecture.usecase

import kotlinx.coroutines.withContext
import tech.stonks.drinkbar.domain.cleanarchitecture.coroutine.CoroutineContextProvider

abstract class BackgroundExecutingUseCase<REQUEST, RESULT>(
    private val _coroutineContextProvider: CoroutineContextProvider
): UseCase<REQUEST, RESULT> {

    final override suspend fun execute(input: REQUEST, onResult: (RESULT) -> Unit) {
        val result = withContext(_coroutineContextProvider.io) {
            executeInBackground(input)
        }
        onResult(result)
    }

    abstract fun executeInBackground(
        request: REQUEST
    ): RESULT
}