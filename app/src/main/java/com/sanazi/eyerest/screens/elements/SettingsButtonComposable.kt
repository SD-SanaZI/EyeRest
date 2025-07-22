package com.sanazi.eyerest.screens.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanazi.eyerest.R
import com.sanazi.eyerest.Settings

@Composable
fun SettingsButtonComposable(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .size(25.dp)
            .clip(RoundedCornerShape(6.dp))
            .border(1.dp, Color.White, RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.tertiary)
            .clickable { navController.navigate(Settings) },
        contentAlignment = Alignment.Center
    ) {
        Image(painterResource(R.drawable.settings_icon),null,
            Modifier.size(13.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary))
    }
}