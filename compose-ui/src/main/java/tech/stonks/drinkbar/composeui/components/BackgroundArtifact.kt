package tech.stonks.drinkbar.composeui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun BackgroundArtifact() {
    return Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(CurvedShape()),
        content = {
            Box(
                Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )
        }
    )
}

class CurvedShape : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        return Outline.Generic(Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            cubicTo(size.width, size.height, size.width / 2, size.height / 2, 0f, 0f)
            close()
        })
    }
}