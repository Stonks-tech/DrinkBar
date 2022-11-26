package tech.stonks.drinkbar.presentation.drinklist.model

import tech.stonks.drinkbar.presentation.architecture.model.PresentationDestination

sealed interface DrinkListPresentationDestination : PresentationDestination {
    data class DrinkDetails(val drinkId: String) : DrinkListPresentationDestination
}