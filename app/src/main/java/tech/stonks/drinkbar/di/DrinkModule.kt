package tech.stonks.drinkbar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.data.drink.datasource.DrinkDataSource
import tech.stonks.drinkbar.datasource.drink.datasource.DrinkLiveDataSource
import tech.stonks.drinkbar.datasource.drink.mapper.DrinkApiToDataMapper
import tech.stonks.drinkbar.datasource.drink.service.DrinkService

@Module
@InstallIn(SingletonComponent::class)
class DrinkModule {

    @Provides
    fun providesDrinkApiDataSourceToDataModelMapper(): DrinkApiToDataMapper {
        return DrinkApiToDataMapper()
    }

    @Provides
    fun providesDrinkLiveApiDataSource(
        drinkApiDataSourceToDataModelMapper: DrinkApiToDataMapper,
        drinkService: DrinkService
    ): DrinkDataSource {
        return DrinkLiveDataSource(drinkService, drinkApiDataSourceToDataModelMapper)
    }
}