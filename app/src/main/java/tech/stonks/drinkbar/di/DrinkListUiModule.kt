package tech.stonks.drinkbar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.composeui.drinklist.mapper.DrinkListDestinationToUiMapper
import tech.stonks.drinkbar.composeui.drinklist.mapper.DrinkListNotificationPresentationToUiMapper
import tech.stonks.drinkbar.composeui.drinklist.mapper.DrinkPresentationToUiMapper
import tech.stonks.drinkbar.navigation.AppDrinkListDestinationToUiMapper

@Suppress("UNCHECKED_CAST")
@Module
@InstallIn(SingletonComponent::class)
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
}