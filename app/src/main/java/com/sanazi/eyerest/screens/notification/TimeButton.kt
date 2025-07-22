package com.sanazi.eyerest.screens.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanazi.eyerest.R

@Composable
fun TimeButton(
    time: Time,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier
            .height(38.dp)
            .width(57.dp)
            .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(7.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(R.string.time,time.hour,time.minute),
            lineHeight = 23.sp,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onTertiary
        )
        Image(
            painterResource(R.drawable.delete_time_icon), null,
            Modifier
                .size(16.dp)
                .align(Alignment.TopEnd)
                .offset(8.dp, -(8).dp)
                .clickable {
                    onDeleteClick.invoke()
                }
        )
    }
}