@file:OptIn(ExperimentalMaterial3Api::class)

package tech.stonks.drinkbar.composeui.drinklist.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
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
                Loading()
            } else {
                DrinkList(drinkList)
            }
        }
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun DrinkList(drinkList: List<DrinkUiModel>) {
    LazyColumn(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.drink_list_padding))
            .fillMaxWidth()
    ) {
        items(drinkList.size) { index ->
            DrinkItem(drinkList[index])
        }
    }
}

@Composable
private fun DrinkItem(drink: DrinkUiModel) {
    Box(
        modifier = Modifier
            .padding(
                PaddingValues(
                    horizontal = dimensionResource(id = R.dimen.drink_list_item_padding_horizontal),
                    vertical = dimensionResource(id = R.dimen.drink_list_item_padding_vertical)
                )
            )
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(
                dimensionResource(id = R.dimen.drink_list_item_corner_radius)
            ),
        ) {
            Column {
                AsyncImage(
                    model = drink.thumbnail,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.drink_list_item_image_height)),
                    contentDescription = stringResource(
                        id = R.string.drink_list_item_image_content_description,
                        drink.name
                    ),
                    contentScale = ContentScale.FillWidth
                )
                Box(
                    modifier = Modifier.padding(
                        PaddingValues(
                            start = dimensionResource(id = R.dimen.drink_list_item_padding),
                            end = dimensionResource(id = R.dimen.drink_list_item_padding),
                            top = dimensionResource(id = R.dimen.drink_list_item_content_space),
                            bottom = dimensionResource(id = R.dimen.drink_list_item_title_space)
                        )
                    )
                ) {
                    Text(text = drink.name, style = MaterialTheme.typography.headlineSmall)
                }
                Box(
                    modifier = Modifier.padding(
                        PaddingValues(
                            start = dimensionResource(id = R.dimen.drink_list_item_padding),
                            end = dimensionResource(id = R.dimen.drink_list_item_padding),
                            bottom = dimensionResource(id = R.dimen.drink_list_item_padding),
                        )
                    )
                ) {
                    Text(
                        text = drink.description,
                        style = MaterialTheme.typography.bodyMedium, maxLines = 3
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun DrinkListPagePreview() {
    DrinkListPage(
        drinkList = listOf(
            DrinkUiModel(
                name = "Drink 1",
                thumbnail = "https://www.thecocktaildb.com/images/media/drink/2x8thr1504816928.jpg",
                description = "Lorem ipsum"
            ),
            DrinkUiModel(
                name = "Drink 1",
                thumbnail = "https://www.thecocktaildb.com/images/media/drink/2x8thr1504816928.jpg",
                description = "Lorem ipsum"
            ),
        ),
        isLoading = false
    )
}