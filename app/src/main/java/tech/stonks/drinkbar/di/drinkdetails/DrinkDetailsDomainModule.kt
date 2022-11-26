package tech.stonks.drinkbar.di.drinkdetails

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import tech.stonks.drinkbar.domain.drinkdetails.repository.GetDrinkRepository
import tech.stonks.drinkbar.domain.drinkdetails.usecase.GetDrinkDetailsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DrinkDetailsDomainModule {
    @Provides
    @Singleton
    fun providesGetDrinkDetailsUseCase(
        getDrinkRepository: GetDrinkRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetDrinkDetailsUseCase {
        return GetDrinkDetailsUseCase(getDrinkRepository, coroutineContextProvider)
    }
}