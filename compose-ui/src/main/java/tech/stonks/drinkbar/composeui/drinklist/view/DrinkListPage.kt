@file:OptIn(ExperimentalMaterial3Api::class)

package tech.stonks.drinkbar.composeui.drinklist.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import tech.stonks.drinkbar.composeui.R
import tech.stonks.drinkbar.composeui.drinklist.model.DrinkUiModel
import tech.stonks.drinkbar.presentation.drinklist.model.DrinkListState
import tech.stonks.drinkbar.presentation.drinklist.viewmodel.DrinkListViewModel

@Composable
fun DrinkListPage(
    viewModel: DrinkListViewModel,
    dependencyProvider: DrinkListDependencyProvider
) {

    LaunchedEffect(viewModel) {
        viewModel.onEntered()
    }

    val state by viewModel.state.observeAsState(DrinkListState())
    val drinkList = state.drinks.map(dependencyProvider.drinkMapper::map)
    DrinkListPage(drinkList, state.isLoading)
}

@Composable
private fun DrinkListPage(
    drinkList: List<DrinkUiModel>,
    isLoading: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.drink_list_title)) }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                DrinkList(drinkList)
            }
        }
    }
}

@Composable
private fun DrinkList(drinkList: List<DrinkUiModel>) {
    LazyColumn {
        items(drinkList.size) { index ->
            DrinkItem(drinkList[index])
        }
    }
}

@Composable
private fun DrinkItem(drink: DrinkUiModel) {
    Text(text = drink.name)
}