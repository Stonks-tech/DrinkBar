package tech.stonks.drinkbar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.data.drink.datasource.DrinkApiDataSource
import tech.stonks.drinkbar.data.drinklist.mapper.DrinkDataModelToDomainModelMapper
import tech.stonks.drinkbar.data.drinklist.repository.DrinkListLiveRepository
import tech.stonks.drinkbar.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import tech.stonks.drinkbar.domain.drinklist.repository.DrinkListRepository
import tech.stonks.drinkbar.domain.drinklist.usecase.GetDrinkListUseCase
import tech.stonks.drinkbar.navigation.AppDrinkListDestinationToUiMapper
import tech.stonks.drinkbar.presentation.drinklist.mapper.DrinkDomainToPresentationModelMapper
import tech.stonks.drinkbar.presentation.drinklist.model.DrinkListState
import tech.stonks.drinkbar.xml_ui.architecture.mapper.ViewStateBinder
import tech.stonks.drinkbar.xml_ui.architecture.view.ViewsProvider
import tech.stonks.drinkbar.xml_ui.drinklist.binder.DrinkListStateBinder
import tech.stonks.drinkbar.xml_ui.drinklist.mapper.DrinkListDestinationToUiMapper
import tech.stonks.drinkbar.xml_ui.drinklist.mapper.DrinkListNotificationPresentationToUiMapper
import tech.stonks.drinkbar.xml_ui.drinklist.mapper.DrinkPresentationToUiMapper

@Suppress("UNCHECKED_CAST")
@Module
@InstallIn(SingletonComponent::class)
class DrinkListModule {
    @Provides
    fun providesDrinkListRepository(
        drinkDataModelToDomainModelMapper: DrinkDataModelToDomainModelMapper,
        drinkApiDataSource: DrinkApiDataSource
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
    fun providesDrinkDataModelToDomainMapper(): DrinkDataModelToDomainModelMapper {
        return DrinkDataModelToDomainModelMapper()
    }

    @Provides
    fun providesDrinkDomainToPresentationModelMapper(): DrinkDomainToPresentationModelMapper {
        return DrinkDomainToPresentationModelMapper()
    }
}