package tech.stonks.drinkbar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.EntryPoints
import tech.stonks.drinkbar.composeui.drinklist.view.DrinkListPage
import tech.stonks.drinkbar.di.entrypoints.drinklist.DrinkListEntryPoint
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
            val entryPoint = EntryPoints.get(LocalContext.current.applicationContext, DrinkListEntryPoint::class.java)
            val viewModel = hiltViewModel<DrinkListViewModel>()
            DrinkListPage(
                viewModel,
                entryPoint.drinkMapper
            )
        }
    }
}