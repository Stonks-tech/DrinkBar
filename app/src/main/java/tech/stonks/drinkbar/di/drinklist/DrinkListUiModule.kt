package tech.stonks.drinkbar.di.drinklist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.composeui.drinklist.mapper.DrinkListNotificationPresentationToUiMapper
import tech.stonks.drinkbar.composeui.drinklist.mapper.DrinkPresentationToUiMapper

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
}