package tech.stonks.drinkbar.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.stonks.drinkbar.datasource.drink.service.DrinkService

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun providesDrinkService(moshi: Moshi): DrinkService {
        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(DrinkService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create(DrinkService::class.java)
    }
}