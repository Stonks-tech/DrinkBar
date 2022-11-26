package tech.stonks.drinkbar.di.drinklist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import tech.stonks.drinkbar.domain.drinklist.repository.DrinkListRepository
import tech.stonks.drinkbar.domain.drinklist.usecase.GetDrinkListUseCase

@Suppress("UNCHECKED_CAST")
@Module
@InstallIn(SingletonComponent::class)
class DrinkListDomainModule {

    @Provides
    fun providesDrinkListUseCase(
        drinkListRepository: DrinkListRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetDrinkListUseCase {
        return GetDrinkListUseCase(drinkListRepository, coroutineContextProvider)
    }
}