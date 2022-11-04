package tech.stonks.drinkbar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.data.drink.datasource.DrinkApiDataSource
import tech.stonks.drinkbar.datasource.drink.datasource.DrinkLiveApiDataSource
import tech.stonks.drinkbar.datasource.drink.mapper.DrinkApiDataSourceToDataModelMapper
import tech.stonks.drinkbar.datasource.drink.service.DrinkService

@Module
@InstallIn(SingletonComponent::class)
class DrinkModule {

    @Provides
    fun providesDrinkApiDataSourceToDataModelMapper(): DrinkApiDataSourceToDataModelMapper {
        return DrinkApiDataSourceToDataModelMapper()
    }

    @Provides
    fun providesDrinkLiveApiDataSource(
        drinkApiDataSourceToDataModelMapper: DrinkApiDataSourceToDataModelMapper,
        drinkService: DrinkService
    ): DrinkApiDataSource {
        return DrinkLiveApiDataSource(drinkService, drinkApiDataSourceToDataModelMapper)
    }
}