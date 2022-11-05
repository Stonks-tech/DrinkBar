package tech.stonks.drinkbar.domain

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext
import tech.stonks.drinkbar.domain.cleanarchitecture.coroutine.CoroutineContextProvider

object FakeCoroutineContextProvider: CoroutineContextProvider {
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
}