package tech.stonks.drinkbar.composeui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import tech.stonks.drinkbar.composeui.R

@Composable
fun SearchComposable(value: String, onValueChange: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    if (isExpanded) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconButton(onClick = { isExpanded = false }) {
                Icon(
                    Icons.Rounded.Close,
                    contentDescription = stringResource(
                        id = R.string.close_icon_content_description
                    ),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
            TextField(
                value,
                onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = dimensionResource(id = R.dimen.action_end_padding)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = androidx.compose.ui.graphics.Color.Transparent
                ),
            )
        }
    } else {
        IconButton(onClick = { isExpanded = true }) {
            Icon(
                Icons.Rounded.Search,
                contentDescription = stringResource(
                    id = R.string.search_icon_content_description
                ),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}