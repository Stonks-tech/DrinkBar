package tech.stonks.drinkbar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.data.drink.datasource.DrinkDataSource
import tech.stonks.drinkbar.data.drinklist.mapper.DrinkDataToDomainMapper
import tech.stonks.drinkbar.data.drinklist.repository.DrinkListLiveRepository
import tech.stonks.drinkbar.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import tech.stonks.drinkbar.domain.drinklist.repository.DrinkListRepository
import tech.stonks.drinkbar.domain.drinklist.usecase.GetDrinkListUseCase
import tech.stonks.drinkbar.presentation.drinklist.mapper.DrinkDomainToPresentationMapper

@Suppress("UNCHECKED_CAST")
@Module
@InstallIn(SingletonComponent::class)
class DrinkListModule {
    @Provides
    fun providesDrinkListRepository(
        drinkDataModelToDomainModelMapper: DrinkDataToDomainMapper,
        drinkApiDataSource: DrinkDataSource
    ): DrinkListRepository {
        return DrinkListLiveRepository(drinkApiDataSource, drinkDataModelToDomainModelMapper)
    }

    @Provides
    fun providesDrinkListUseCase(
        drinkListRepository: DrinkListRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetDrinkListUseCase {
        return GetDrinkListUseCase(drinkListRepository, coroutineContextProvider)
    }

    @Provides
    fun providesDrinkDataModelToDomainMapper(): DrinkDataToDomainMapper {
        return DrinkDataToDomainMapper()
    }

    @Provides
    fun providesDrinkDomainToPresentationModelMapper(): DrinkDomainToPresentationMapper {
        return DrinkDomainToPresentationMapper()
    }
}