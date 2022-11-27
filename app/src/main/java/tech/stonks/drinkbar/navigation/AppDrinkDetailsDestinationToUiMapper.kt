package tech.stonks.drinkbar.navigation

import androidx.navigation.NavController
import tech.stonks.drinkbar.composeui.drinkdetails.mapper.DrinkDetailsDestinationToUiMapper
import tech.stonks.drinkbar.presentation.architecture.model.PresentationDestination
import tech.stonks.drinkbar.xml_ui.navigation.model.UiDestination

class AppDrinkDetailsDestinationToUiMapper(
    private val _navController: NavController,
    private val _globalDestinationMapper: GlobalPresentationDestinationToUiMapper
) : DrinkDetailsDestinationToUiMapper {
    override fun toUi(input: PresentationDestination): UiDestination {
        return when (input) {
            else -> _globalDestinationMapper.toUi(input)
        }
    }
}