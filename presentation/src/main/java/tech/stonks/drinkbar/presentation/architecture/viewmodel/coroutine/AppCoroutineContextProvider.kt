package tech.stonks.drinkbar.presentation.architecture.viewmodel.coroutine

import kotlinx.coroutines.Dispatchers
import tech.stonks.drinkbar.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import kotlin.coroutines.CoroutineContext

class AppCoroutineContextProvider: CoroutineContextProvider {
    override val main: CoroutineContext = Dispatchers.Main
    override val io: CoroutineContext = Dispatchers.IO
}