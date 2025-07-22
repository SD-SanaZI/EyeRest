package com.sanazi.eyerest.screens.elements

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerComposable(
    timerState: MutableIntState,
    modifier: Modifier = Modifier
){
    val min = remember { mutableFloatStateOf(0f) }
    Box(
        Modifier.sizeIn(maxHeight = 248.dp, maxWidth = 248.dp)
            .then(modifier)
            .aspectRatio(1f)
            .border(11.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(200.dp))
            .drawBehind {
                min.floatValue = minOf(size.height.toDp().value,size.width.toDp().value * 2 / 3)
            }
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            timerState.intValue.toString(),
            Modifier,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = min.floatValue.sp/1.5,
            lineHeight = min.floatValue.sp/1.5,
            )
    }
}