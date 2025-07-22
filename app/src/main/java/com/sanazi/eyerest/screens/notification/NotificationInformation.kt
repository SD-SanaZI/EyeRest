package com.sanazi.eyerest.screens.notification

import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sanazi.eyerest.R

@Composable
fun NotificationInformation(
    modifier: Modifier = Modifier
) {
    Text(
        stringResource(R.string.notification_information),
        modifier
            .height(46.dp),
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.onSecondary,
        textAlign = TextAlign.Justify
    )
}