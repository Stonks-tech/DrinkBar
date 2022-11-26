@file:OptIn(ExperimentalMaterial3Api::class)

package tech.stonks.drinkbar.composeui.drinkdetails.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import tech.stonks.drinkbar.composeui.drinklist.mapper.DrinkPresentationToUiMapper
import tech.stonks.drinkbar.composeui.drinklist.model.DrinkUiModel
import tech.stonks.drinkbar.presentation.drinkdetails.viewmodel.DrinkDetailsViewModel

@Composable
fun DrinkDetailsPage(
    viewModel: DrinkDetailsViewModel,
    drinkMapper: DrinkPresentationToUiMapper
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.onEntered()
    }
    val state by viewModel.state.observeAsState()

    DrinkDetailsPage(drink = state?.drink?.let(drinkMapper::map))
}

@Composable
private fun DrinkDetailsPage(drink: DrinkUiModel?) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = drink?.name ?: "") })
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Text(
                text = drink?.description ?: ""
            )
        }
    }
}