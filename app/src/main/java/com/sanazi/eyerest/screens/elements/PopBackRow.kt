package com.sanazi.eyerest.screens.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun PopBackRow(
    navController: NavController,
    isToMain: Boolean = false,
    modifier: Modifier = Modifier,
){
    Row(
        modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(navController, isToMain)
        AppNameComposable()
    }
}