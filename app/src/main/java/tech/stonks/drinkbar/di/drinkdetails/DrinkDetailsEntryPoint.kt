package tech.stonks.drinkbar.di.drinkdetails

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.composeui.drinkdetails.view.DrinkDetailsDependencyProvider
import tech.stonks.drinkbar.composeui.drinklist.mapper.DrinkPresentationToUiMapper

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DrinkDetailsEntryPoint : DrinkDetailsDependencyProvider {
    override val drinkMapper: DrinkPresentationToUiMapper
}