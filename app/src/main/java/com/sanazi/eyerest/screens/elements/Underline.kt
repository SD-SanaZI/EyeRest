package com.sanazi.eyerest.screens.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sanazi.eyerest.R

@Composable
fun Underline(
    modifier: Modifier = Modifier
){
    Image(
        painterResource(R.drawable.underline), null,
        modifier
            .width(118.dp)
            .height(11.dp)
    )
}