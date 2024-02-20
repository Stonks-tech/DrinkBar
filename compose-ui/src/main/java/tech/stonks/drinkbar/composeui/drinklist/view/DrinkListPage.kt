@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package tech.stonks.drinkbar.composeui.drinklist.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tech.stonks.drinkbar.composeui.R
import tech.stonks.drinkbar.composeui.components.BackgroundArtifact
import tech.stonks.drinkbar.composeui.components.SearchComposable
import tech.stonks.drinkbar.composeui.drinklist.mapper.DrinkListDestinationToUiMapper
import tech.stonks.drinkbar.composeui.drinklist.mapper.DrinkPresentationToUiMapper
import tech.stonks.drinkbar.composeui.drinklist.model.DrinkUiModel
import tech.stonks.drinkbar.presentation.drinklist.model.DrinkListState
import tech.stonks.drinkbar.presentation.drinklist.viewmodel.DrinkListViewModel

@Composable
fun DrinkListPage(
    viewModel: DrinkListViewModel,
    drinkMapper: DrinkPresentationToUiMapper,
    destinationMapper: DrinkListDestinationToUiMapper,
) {
    LaunchedEffect(viewModel) {
        viewModel.onEntered()
    }
    val state by viewModel.state.observeAsState(DrinkListState())
    val drinkList = state.drinks.map(drinkMapper::map)

    viewModel.destination.observe(LocalLifecycleOwner.current) { destination ->
        destinationMapper.toUi(destination).navigate()
    }

    DrinkListPage(
        drinkList = drinkList,
        isLoading = state.isLoading,
        isCompact = state.isCompact,
        searchQuery = state.searchQuery,
        onSearchQueryChanged = viewModel::onSearchAction,
        onRefresh = viewModel::onRefreshAction,
        onItemClick = viewModel::onItemClicked,
        onCompactClick = viewModel::onCompactAction,
    )
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
private fun DrinkListPage(
    drinkList: List<DrinkUiModel>,
    isLoading: Boolean,
    isCompact: Boolean,
    searchQuery: String = "",
    onRefresh: () -> Unit = {},
    onItemClick: (id: String) -> Unit = {},
    onSearchQueryChanged: (String) -> Unit = {},
    onCompactClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.drink_list_title)) },
                actions = {
                    SearchComposable(value = searchQuery, onValueChange = onSearchQueryChanged)
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                )
            )
        }
    ) { padding ->
        val pullRefreshState = rememberPullRefreshState(
            refreshing = isLoading,
            onRefresh = onRefresh
        )
        Box(
            modifier = Modifier
                .padding(padding)
                .pullRefresh(pullRefreshState)
        ) {
            DrinkList(drinkList, isCompact, onItemClick)
            Box {
                BackgroundArtifact()
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(horizontal = 8.dp),
                    onClick = onCompactClick,
                ) {
                    if (!isCompact) {
                        Icon(
                            Icons.Rounded.List,
                            contentDescription = stringResource(id = R.string.drink_list_icon_compact_content_description),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    } else {
                        Icon(
                            Icons.Sharp.Menu,
                            contentDescription = stringResource(id = R.string.drink_list_icon_compact_content_description),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = isLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
private fun DrinkList(drinkList: List<DrinkUiModel>, isCompact: Boolean, onClick: (id: String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            )
        }
        items(drinkList.size) { index ->
            if(isCompact) {
                DrinkItemCompact(drinkList[index], onClick)
            } else {
                DrinkItemBig(drinkList[index], onClick)
            }
        }
    }
}

@Composable
private fun DrinkItemBig(drink: DrinkUiModel, onClick: (id: String) -> Unit) {
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
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(drink.id) },
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
private fun DrinkItemCompact(drink: DrinkUiModel, onClick: (id: String) -> Unit) {
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
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(drink.id) },
            shape = RoundedCornerShape(
                dimensionResource(id = R.dimen.drink_list_item_corner_radius)
            ),
        ) {
            Row {
                AsyncImage(
                    model = drink.thumbnail,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(dimensionResource(id = R.dimen.drink_list_item_image_width)),
                    contentDescription = stringResource(
                        id = R.string.drink_list_item_image_content_description,
                        drink.name
                    ),
                    contentScale = ContentScale.FillWidth
                )
                Column {
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
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
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
                id = "1",
                name = "Drink 1",
                thumbnail = "https://www.thecocktaildb.com/images/media/drink/2x8thr1504816928.jpg",
                description = "Lorem ipsum",
                ingredients = emptyList()
            ),
            DrinkUiModel(
                id = "2",
                name = "Drink 1",
                thumbnail = "https://www.thecocktaildb.com/images/media/drink/2x8thr1504816928.jpg",
                description = "Lorem ipsum",
                ingredients = emptyList()
            ),
        ),
        isCompact = false,
        isLoading = false
    )
}