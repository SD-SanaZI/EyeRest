package com.sanazi.eyerest.screens.elements

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ExerciseDescriptionLarge(
    @StringRes stringRes: Int,
    modifier: Modifier = Modifier
){
    Text(
        stringResource(stringRes),
        modifier
            .fillMaxWidth()
            .height(115.dp),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSecondary,
        textAlign = TextAlign.Center
    )
}