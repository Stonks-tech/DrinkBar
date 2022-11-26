package tech.stonks.drinkbar.composeui.drinkdetails.view

import tech.stonks.drinkbar.composeui.drinklist.mapper.DrinkPresentationToUiMapper

interface DrinkDetailsDependencyProvider {
    val drinkMapper: DrinkPresentationToUiMapper
}