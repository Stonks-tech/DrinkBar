package tech.stonks.drinkbar.di.drink

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.data.drink.mapper.DrinkDataToDomainMapper
import tech.stonks.drinkbar.data.drink.mapper.IngredientDataToDomainMapper

@InstallIn(SingletonComponent::class)
@Module
class DrinkDataModule {
    @Provides
    fun providesDrinkDataModelToDomainMapper(ingredientDataToDomainMapper: IngredientDataToDomainMapper): DrinkDataToDomainMapper {
        return DrinkDataToDomainMapper(ingredientDataToDomainMapper)
    }

    @Provides
    fun providesIngredientDataModelToDomainMapper(): IngredientDataToDomainMapper {
        return IngredientDataToDomainMapper()
    }
}