package tech.stonks.drinkbar.di.drinklist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.presentation.drink.mapper.DrinkDomainToPresentationMapper

@Module
@InstallIn(SingletonComponent::class)
class DrinkListPresentationModule {

    @Provides
    fun providesDrinkDomainToPresentationModelMapper(): DrinkDomainToPresentationMapper {
        return DrinkDomainToPresentationMapper()
    }
}