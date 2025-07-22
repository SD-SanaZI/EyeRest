package com.sanazi.eyerest.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanazi.eyerest.R
import com.sanazi.eyerest.screens.elements.PopBackRow

@Composable
fun Settings(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        PopBackRow(navController)
        Text(
            stringResource(R.string.settings),
            Modifier
                .padding(top = 26.dp)
                .fillMaxWidth()
                .height(23.dp),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Visible
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(25.dp, 55.dp)
                .verticalScroll(rememberScrollState(0)),
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalAlignment = Alignment.Start
        ) {
            NotificationSettings()
            DurationOfVibrationSettings()
            DotSpeedSettings()
        }
    }
}

