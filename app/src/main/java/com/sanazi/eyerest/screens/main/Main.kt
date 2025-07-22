package com.sanazi.eyerest.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanazi.eyerest.Exercise1
import com.sanazi.eyerest.screens.elements.AppNameComposable
import com.sanazi.eyerest.screens.elements.BorderLine
import com.sanazi.eyerest.screens.elements.SettingsButtonComposable
import com.sanazi.eyerest.screens.main.carousel.CarouselOfImagesComposable

@Composable
fun Main(
    navController: NavController,
    modifier: Modifier = Modifier
){
    Column(
        modifier.fillMaxSize().padding(20.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            AppNameComposable()
            SettingsButtonComposable(navController)
        }
        CarouselOfImagesComposable(navController,Modifier.height(240.dp))
        Column(
            Modifier.fillMaxWidth().height(270.dp),
            Arrangement.SpaceBetween
        ) {
            BorderLine()
            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                ExerciseListButton(navController)
                StereogramsButton(navController)
            }
            NotificationsButton(navController)
            BorderLine()
        }
        QuickStartButton{ navController.navigate(Exercise1(true)) }
    }
}

