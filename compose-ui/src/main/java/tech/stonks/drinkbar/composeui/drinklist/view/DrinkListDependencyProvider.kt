package tech.stonks.drinkbar.composeui.drinklist.view

import tech.stonks.drinkbar.composeui.drinklist.mapper.DrinkPresentationToUiMapper

interface DrinkListDependencyProvider {
    val drinkMapper: DrinkPresentationToUiMapper
}