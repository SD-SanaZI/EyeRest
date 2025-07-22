package com.sanazi.eyerest.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sanazi.eyerest.R

@Composable
fun TryButton(
    modifier: Modifier = Modifier,
    onClick: ()->Unit
){
    Box(
        modifier
            .height(27.dp)
            .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(6.dp))
            .clickable { onClick.invoke() }
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(R.string.try_str),
            Modifier.height(23.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}