package tech.stonks.drinkbar.navigation

import androidx.navigation.NavController
import tech.stonks.drinkbar.composeui.drinklist.mapper.DrinkListDestinationToUiMapper
import tech.stonks.drinkbar.presentation.architecture.model.PresentationDestination
import tech.stonks.drinkbar.presentation.drinklist.model.DrinkListPresentationDestination
import tech.stonks.drinkbar.xml_ui.navigation.model.UiDestination

class AppDrinkListDestinationToUiMapper(
    private val _navController: NavController,
) : DrinkListDestinationToUiMapper {
    override fun toUi(input: PresentationDestination): UiDestination {
        when (input) {
            is DrinkListPresentationDestination.DrinkDetails -> {
                return DrinkDetailsDestination(input.drinkId, _navController)
            }
            else -> {
                throw IllegalStateException("Unknown destination: $input")
            }
        }
    }

    private data class DrinkDetailsDestination(
        override val drinkId: String,
        private val _navController: NavController
    ) : DrinkListDestinationToUiMapper.DrinkDetailsDestination() {
        override fun navigate() {
            _navController.navigate("drink/$drinkId")
        }
    }
}