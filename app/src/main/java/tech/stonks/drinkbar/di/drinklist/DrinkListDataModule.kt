package tech.stonks.drinkbar.di.drinklist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.data.drink.datasource.DrinkDataSource
import tech.stonks.drinkbar.data.drink.mapper.DrinkDataToDomainMapper
import tech.stonks.drinkbar.data.drinklist.repository.DrinkListRepositoryImpl
import tech.stonks.drinkbar.data.drinklist.repository.SearchDrinksRepositoryImpl
import tech.stonks.drinkbar.domain.drinklist.repository.DrinkListRepository
import tech.stonks.drinkbar.domain.drinklist.repository.SearchDrinksRepository

@Module
@InstallIn(SingletonComponent::class)
class DrinkListDataModule {
    @Provides
    fun providesDrinkListRepository(
        drinkDataModelToDomainModelMapper: DrinkDataToDomainMapper,
        drinkApiDataSource: DrinkDataSource
    ): DrinkListRepository {
        return DrinkListRepositoryImpl(drinkApiDataSource, drinkDataModelToDomainModelMapper)
    }

    @Provides
    fun providesSearchDrinksRepository(
        drinkDataModelToDomainModelMapper: DrinkDataToDomainMapper,
        drinkApiDataSource: DrinkDataSource
    ): SearchDrinksRepository {
        return SearchDrinksRepositoryImpl(drinkApiDataSource, drinkDataModelToDomainModelMapper)
    }
}