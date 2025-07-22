package com.sanazi.eyerest.screens.elements

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.alphaScroll(bottomPadding: Float): Modifier {
    val scrollState = rememberScrollState()
    return this
        .verticalScroll(scrollState)
        .graphicsLayer { alpha = 0.99f }
        .drawWithContent {
            drawContent()
            if (scrollState.canScrollForward) {
                val end = size.minDimension * 2 + scrollState.value.toFloat()
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Black, Color.Transparent),
                        startY = end - 1 - (bottomPadding * (1 - scrollState.value.toFloat() / scrollState.maxValue)).dp.toPx(),
                        endY = end
                    ),
                    blendMode = BlendMode.DstIn,
                )
            }
        }
}