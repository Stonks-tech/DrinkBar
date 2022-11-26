package tech.stonks.drinkbar.di.drinklist

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.stonks.drinkbar.composeui.drinklist.view.DrinkListDependencyProvider

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DrinkListEntryPoint : DrinkListDependencyProvider
