package com.sanazi.eyerest.screens.elements

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sanazi.eyerest.R

@Composable
fun ExerciseNumber(
    number: Int,
    modifier: Modifier = Modifier
) {
    Text(
        stringResource(R.string.exercise, number),
        modifier,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.primary
    )
}