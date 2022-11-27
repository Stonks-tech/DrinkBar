package tech.stonks.drinkbar.domain

import kotlinx.coroutines.Dispatchers
import tech.stonks.drinkbar.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import kotlin.coroutines.CoroutineContext

object FakeCoroutineContextProvider : CoroutineContextProvider {
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
}