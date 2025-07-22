package com.sanazi.eyerest.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanazi.eyerest.ExerciseList
import com.sanazi.eyerest.R

@Composable
fun ExerciseListButton(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .size(146.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable { navController.navigate(ExerciseList) }
    ) {
        Image(
            painterResource(R.drawable.exercise_list_icon), null,
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 14.dp)
                .width(54.dp)
                .height(81.dp)
        )
        Text(
            stringResource(R.string.exercise_list).uppercase(),
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 23.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondaryContainer
        )
    }
}