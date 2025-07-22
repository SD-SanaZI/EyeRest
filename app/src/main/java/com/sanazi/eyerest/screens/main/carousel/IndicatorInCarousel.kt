package com.sanazi.eyerest.screens.main.carousel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun CarouselOfImagesIndicator(
    isActive: Boolean,
    modifier: Modifier = Modifier
){
    Box(
        modifier
            .size(8.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(
                if (isActive)
                    MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary
            )
    )
}