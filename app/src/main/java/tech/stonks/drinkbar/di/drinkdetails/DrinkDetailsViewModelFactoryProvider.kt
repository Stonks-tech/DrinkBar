package tech.stonks.drinkbar.di.drinkdetails

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import tech.stonks.drinkbar.di.ViewModelFactoryProvider
import tech.stonks.drinkbar.presentation.drinkdetails.viewmodel.DrinkDetailsViewModel

@EntryPoint
@InstallIn(ActivityComponent::class)
interface DrinkDetailsViewModelFactoryProvider :
    ViewModelFactoryProvider<DrinkDetailsViewModel.DrinkDetailsViewModelFactory> {
    override fun provide(): DrinkDetailsViewModel.DrinkDetailsViewModelFactory
}