package com.sanazi.eyerest.screens.exerciseList

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
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
import com.sanazi.eyerest.NavRoutes
import com.sanazi.eyerest.R

data class Exercise(
    val id: Int,
    @StringRes val text: Int,
    @DrawableRes val image: Int,
    val navRoute: NavRoutes
)

@Composable
fun ExerciseElement(
    exercise: Exercise,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxWidth()
    ) {
        Text(
            stringResource(R.string.exercise, exercise.id),
            Modifier
                .align(Alignment.TopStart)
                .height(23.dp),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Image(
            painterResource(R.drawable.underline), null,
            Modifier
                .align(Alignment.TopStart)
                .padding(top = 27.dp)
                .clip(AbsoluteCutCornerShape(0.dp))
                .offset(x = (-26).dp)
                .width(118.dp)
                .height(11.dp)
        )
        Text(
            stringResource(exercise.text),
            Modifier
                .align(Alignment.TopStart)
                .padding(top = 55.dp)
                .width(200.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Image(
            painterResource(exercise.image), null,
            Modifier
                .align(Alignment.BottomEnd)
                .size(64.dp)
                .clip(RoundedCornerShape(9.dp))
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}