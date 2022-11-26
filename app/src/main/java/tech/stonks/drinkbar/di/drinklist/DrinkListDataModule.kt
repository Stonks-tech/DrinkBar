package tech.stonks.drinkbar.di.drinklist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.data.drink.datasource.DrinkDataSource
import tech.stonks.drinkbar.data.drink.mapper.DrinkDataToDomainMapper
import tech.stonks.drinkbar.data.drinklist.repository.DrinkListRepositoryImpl
import tech.stonks.drinkbar.domain.drinklist.repository.DrinkListRepository

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
    fun providesDrinkDataModelToDomainMapper(): DrinkDataToDomainMapper {
        return DrinkDataToDomainMapper()
    }
}