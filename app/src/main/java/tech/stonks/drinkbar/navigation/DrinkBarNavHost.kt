package tech.stonks.drinkbar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import tech.stonks.drinkbar.composeui.drinkdetails.view.DrinkDetailsDependencyProvider
import tech.stonks.drinkbar.composeui.drinkdetails.view.DrinkDetailsPage
import tech.stonks.drinkbar.composeui.drinklist.view.DrinkListDependencyProvider
import tech.stonks.drinkbar.composeui.drinklist.view.DrinkListPage
import tech.stonks.drinkbar.di.drinkdetails.DrinkDetailsEntryPoint
import tech.stonks.drinkbar.di.drinkdetails.DrinkDetailsViewModelFactoryProvider
import tech.stonks.drinkbar.di.drinklist.DrinkListEntryPoint
import tech.stonks.drinkbar.di.utils.hiltActivityEntryPoint
import tech.stonks.drinkbar.di.utils.hiltEntryPoint
import tech.stonks.drinkbar.presentation.drinkdetails.viewmodel.DrinkDetailsViewModel
import tech.stonks.drinkbar.presentation.drinklist.viewmodel.DrinkListViewModel

@Composable
fun DrinkBarNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "drink_list",
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("drink_list") {
            val dependencyProvider =
                hiltEntryPoint<DrinkListEntryPoint>() as DrinkListDependencyProvider
            val viewModel = hiltViewModel<DrinkListViewModel>()
            DrinkListPage(
                viewModel,
                dependencyProvider.drinkMapper,
                AppDrinkListDestinationToUiMapper(navController)
            )
        }
        composable(
            "drink/{drinkId}",
            arguments = listOf(
                navArgument("drinkId") {
                    type = NavType.StringType
                }
            ),
        ) {
            val drinkId = it.arguments?.getString("drinkId")
                ?: throw IllegalArgumentException("drinkId is required")
            val factory = hiltActivityEntryPoint<DrinkDetailsViewModelFactoryProvider>().provide()
            val viewModel: DrinkDetailsViewModel = viewModel(
                factory = DrinkDetailsViewModel.DrinkDetailsViewModelFactory.provideFactory(
                    factory,
                    drinkId
                )
            )
            val drinkDetailsDependencyProvider =
                hiltEntryPoint<DrinkDetailsEntryPoint>() as DrinkDetailsDependencyProvider

            DrinkDetailsPage(viewModel, drinkDetailsDependencyProvider.drinkMapper)
        }
    }
}