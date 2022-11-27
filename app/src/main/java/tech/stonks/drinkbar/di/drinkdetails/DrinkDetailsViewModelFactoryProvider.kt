package tech.stonks.drinkbar.di.drinkdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import tech.stonks.drinkbar.di.ViewModelFactoryProvider
import tech.stonks.drinkbar.presentation.drinkdetails.viewmodel.DrinkDetailsViewModel

@EntryPoint
@InstallIn(ActivityComponent::class)
interface DrinkDetailsViewModelFactoryProvider :
    ViewModelFactoryProvider<DrinkDetailsViewModelFactory> {
    override fun provide(): DrinkDetailsViewModelFactory
}

@AssistedFactory
interface DrinkDetailsViewModelFactory {
    fun create(drinkId: String): DrinkDetailsViewModel

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: DrinkDetailsViewModelFactory,
            drinkId: String
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(drinkId) as T
                }
            }
        }
    }
}