package tech.stonks.drinkbar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
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
@InstallIn(FragmentComponent::class)
class DrinkListUiModule {

    @Provides
    fun providesDrinkPresentationToUiMapper(): DrinkPresentationToUiMapper {
        return DrinkPresentationToUiMapper()
    }

    @Provides
    fun providesDrinkListNotificationPresentationToUiMapper(): DrinkListNotificationPresentationToUiMapper {
        return DrinkListNotificationPresentationToUiMapper()
    }

    @Provides
    fun providesDrinkListDestinationToUiMapper(): DrinkListDestinationToUiMapper {
        return AppDrinkListDestinationToUiMapper()
    }

    @Provides
    @FragmentScoped
    fun providesDrinkListBinder(drinkPresentationToUiMapper: DrinkPresentationToUiMapper): ViewStateBinder<DrinkListState, ViewsProvider> {
        return DrinkListStateBinder(drinkPresentationToUiMapper) as ViewStateBinder<DrinkListState, ViewsProvider>
    }
}