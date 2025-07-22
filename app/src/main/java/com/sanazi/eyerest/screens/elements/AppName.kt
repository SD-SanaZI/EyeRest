package com.sanazi.eyerest.screens.elements

import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sanazi.eyerest.R

@Composable
fun AppNameComposable(
    modifier: Modifier = Modifier
) {
    Text(
        stringResource(R.string.app_name),
        modifier.height(23.dp),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSecondary
    )
}