package com.sanazi.eyerest.screens.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sanazi.eyerest.R

@Composable
fun IsSelectedButton(
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .size(25.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.tertiary),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Image(
                painterResource(R.drawable.selected_icon), null,
                Modifier
                    .width(13.dp)
                    .height(20.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }
    }
}