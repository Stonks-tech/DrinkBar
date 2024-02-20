@file:OptIn(ExperimentalMaterial3Api::class)

package tech.stonks.drinkbar.composeui.drinkdetails.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import tech.stonks.drinkbar.composeui.R
import tech.stonks.drinkbar.composeui.components.BackgroundArtifact
import tech.stonks.drinkbar.composeui.components.Loading
import tech.stonks.drinkbar.composeui.components.StatusComposable
import tech.stonks.drinkbar.composeui.drinkdetails.mapper.DrinkDetailsDestinationToUiMapper
import tech.stonks.drinkbar.composeui.drinklist.mapper.DrinkPresentationToUiMapper
import tech.stonks.drinkbar.composeui.drinklist.model.DrinkUiModel
import tech.stonks.drinkbar.presentation.drinkdetails.model.DrinkDetailsState
import tech.stonks.drinkbar.presentation.drinkdetails.viewmodel.DrinkDetailsViewModel

@Composable
fun DrinkDetailsPage(
    viewModel: DrinkDetailsViewModel,
    drinkMapper: DrinkPresentationToUiMapper,
    destinationMapper: DrinkDetailsDestinationToUiMapper
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.onEntered()
    }
    val state by viewModel.state.observeAsState(DrinkDetailsState())

    viewModel.destination.observe(LocalLifecycleOwner.current) { destination ->
        destinationMapper.toUi(destination).navigate()
    }

    DrinkDetailsPage(
        drink = state.drink?.let(drinkMapper::map),
        isLoading = state.isLoading,
        onBackPressed = viewModel::onBackAction,
        onTryAgain = viewModel::onRetryAction
    )
}

@Composable
private fun DrinkDetailsPage(
    drink: DrinkUiModel?,
    isLoading: Boolean,
    onBackPressed: () -> Unit = {},
    onTryAgain: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = drink?.name ?: "") },
                navigationIcon = {
                    IconButton(
                        onClick = onBackPressed
                    ) {
                        Icon(
                            Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.back_content_description)
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            BackgroundArtifact()
            Box(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.drink_details_page_padding))
                    .fillMaxWidth()
            ) {
                when {
                    isLoading -> Loading()
                    drink != null -> DrinkContent(drink)
                    else -> StatusComposable(
                        image = {
                            Icon(
                                imageVector = Icons.Rounded.Warning,
                                contentDescription = stringResource(id = R.string.error_unknown_icon_content_description)
                            )
                        },
                        header = stringResource(id = R.string.error_header_unknown),
                        message = stringResource(
                            id = R.string.error_message_unknown
                        ),
                        primaryButton = {
                            Button(
                                content = {
                                    Text(text = stringResource(id = R.string.error_button_retry))
                                },
                                onClick = onTryAgain,
                                colors = ButtonDefaults.buttonColors()
                            )
                        }
                    )
                }
            }
        }
    }
}


@Composable
private fun DrinkContent(drink: DrinkUiModel) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = drink.thumbnail,
                contentScale = ContentScale.FillWidth,
                contentDescription = stringResource(
                    id = R.string.drink_list_item_image_content_description
                )
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(
                        id = R.dimen.drink_details_card_bottom_padding
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.drink_details_card_padding))
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.drink_details_instructions),
                        modifier = Modifier.padding(
                            bottom = dimensionResource(
                                id = R.dimen.drink_details_headline_padding_bottom
                            )
                        ),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = drink.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        if (drink.ingredients.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(
                            id = R.dimen.drink_details_card_bottom_padding
                        )
                    )
            ) {
                Box(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.drink_details_card_padding))
                ) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.drink_details_ingredients),
                            modifier = Modifier.padding(
                                bottom = dimensionResource(
                                    id = R.dimen.drink_details_headline_padding_bottom
                                )
                            ),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Column {
                            drink.ingredients.forEachIndexed { index, ingredient ->
                                IngredientItem(
                                    ingredient,
                                    index == drink.ingredients.lastIndex
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun IngredientItem(text: String, isLast: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(
                        id = R.dimen.drink_details_ingredient_item_padding_vertical
                    )
                ),
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
        if (!isLast) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
private fun DrinkDetailsPagePreview() {
    DrinkDetailsPage(
        isLoading = false,
        drink = DrinkUiModel(
            id = "1",
            name = "Margarita",
            thumbnail = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg",
            description = "Shake and strain into a chilled cocktail glass.",
            ingredients = listOf(
                "1 1/2 oz Tequila",
                "1 oz Triple sec",
                "1 oz Lime juice",
                "1 oz Orange juice"
            )
        )
    )
}