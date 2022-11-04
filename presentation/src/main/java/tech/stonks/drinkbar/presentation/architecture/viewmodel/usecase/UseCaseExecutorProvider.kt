package tech.stonks.drinkbar.presentation.architecture.viewmodel.usecase

import kotlinx.coroutines.CoroutineScope
import tech.stonks.drinkbar.domain.cleanarchitecture.usecase.UseCaseExecutor

typealias UseCaseExecutorProvider = @JvmSuppressWildcards (coroutineScope: CoroutineScope) -> UseCaseExecutor