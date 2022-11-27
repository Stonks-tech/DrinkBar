package tech.stonks.drinkbar.navigation

import androidx.navigation.NavController
import tech.stonks.drinkbar.presentation.architecture.model.PresentationDestination
import tech.stonks.drinkbar.xml_ui.navigation.mapper.DestinationPresentationToUiMapper
import tech.stonks.drinkbar.xml_ui.navigation.model.UiDestination

class GlobalPresentationDestinationToUiMapper(
    private val _navController: NavController
) : DestinationPresentationToUiMapper {
    override fun toUi(input: PresentationDestination): UiDestination {
        return when (input) {
            PresentationDestination.Back -> Back(_navController)
            else -> throw IllegalArgumentException("Unknown destination: $input")
        }
    }

    private class Back(private val _navController: NavController) : UiDestination {
        override fun navigate() {
            _navController.navigateUp()
        }
    }
}