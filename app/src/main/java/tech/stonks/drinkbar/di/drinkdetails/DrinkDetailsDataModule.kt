package tech.stonks.drinkbar.di.drinkdetails

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.data.drink.datasource.DrinkDataSource
import tech.stonks.drinkbar.data.drink.mapper.DrinkDataToDomainMapper
import tech.stonks.drinkbar.data.drinkdetails.repository.GetDrinkRepositoryImpl
import tech.stonks.drinkbar.domain.drinkdetails.repository.GetDrinkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DrinkDetailsDataModule {

    @Provides
    @Singleton
    fun provideDrinkDetailsRepository(
        drinkDataSource: DrinkDataSource,
        drinkDataToDomainMapper: DrinkDataToDomainMapper
    ): GetDrinkRepository {
        return GetDrinkRepositoryImpl(
            drinkDataSource,
            drinkDataToDomainMapper
        )
    }
}