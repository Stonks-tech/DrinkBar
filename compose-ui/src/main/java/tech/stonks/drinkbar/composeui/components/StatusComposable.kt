package tech.stonks.drinkbar.composeui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import tech.stonks.drinkbar.composeui.R

@Composable
fun StatusComposable(
    image: @Composable () -> Unit,
    header: String,
    message: String,
    primaryButton: @Composable () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            image()
            Text(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.status_item_spacing)),
                text = header,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.status_item_spacing)),
                text = message,
                style = MaterialTheme.typography.bodyMedium
            )
            Box(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.status_item_spacing))) {
                primaryButton()
            }
        }
    }
}