package tech.stonks.drinkbar.di.drink

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.presentation.drink.mapper.DrinkDomainToPresentationMapper
import tech.stonks.drinkbar.presentation.drink.mapper.IngredientDomainToPresentationMapper

@Module
@InstallIn(SingletonComponent::class)
class DrinkPresentationModule {

    @Provides
    fun providesDrinkDomainToPresentationModelMapper(ingredientDomainToPresentationMapper: IngredientDomainToPresentationMapper): DrinkDomainToPresentationMapper {
        return DrinkDomainToPresentationMapper(ingredientDomainToPresentationMapper)
    }

    @Provides
    fun providesIngredientDomainToPresentationModelMapper(): IngredientDomainToPresentationMapper {
        return IngredientDomainToPresentationMapper()
    }
}