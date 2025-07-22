package com.sanazi.eyerest.screens.exerciseList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanazi.eyerest.Exercise1
import com.sanazi.eyerest.Exercise2
import com.sanazi.eyerest.Exercise3
import com.sanazi.eyerest.Exercise4
import com.sanazi.eyerest.Exercise5
import com.sanazi.eyerest.Exercise6
import com.sanazi.eyerest.Exercise7
import com.sanazi.eyerest.Exercise8
import com.sanazi.eyerest.R
import com.sanazi.eyerest.screens.elements.BorderLine
import com.sanazi.eyerest.screens.elements.PopBackRow

val exerciseList =
    listOf(
        Exercise(
            1,
            R.string.exercise_1_short,
            R.drawable.exercise_1,
            Exercise1(false)
        ),
        Exercise(
            2,
            R.string.exercise_2_short,
            R.drawable.exercise_2,
            Exercise2(false)
        ),
        Exercise(
            3,
            R.string.exercise_3_short,
            R.drawable.exercise_3,
            Exercise3(false)
        ),
        Exercise(
            4,
            R.string.exercise_4_short,
            R.drawable.exercise_4,
            Exercise4(false)
        ),
        Exercise(
            5,
            R.string.exercise_5_short,
            R.drawable.exercise_5,
            Exercise5(false)
        ),
        Exercise(
            6,
            R.string.exercise_6_short,
            R.drawable.exercise_6,
            Exercise6(false)
        ),
        Exercise(
            7,
            R.string.exercise_7_short,
            R.drawable.exercise_7,
            Exercise7(false)
        ),
        Exercise(
            8,
            R.string.exercise_8_short,
            R.drawable.exercise_8,
            Exercise8(false)
        )
    )


@Composable
fun ExerciseList(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val list = remember { exerciseList }
    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top
    ) {
        PopBackRow(navController, modifier = Modifier.padding(bottom = 25.dp))
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(23.dp)
        ) {
            list.forEachIndexed { index, exercise ->
                ExerciseElement(
                    exercise,
                    Modifier.clickable { navController.navigate(exercise.navRoute) })
                if (index != list.size - 1) {
                    BorderLine(Modifier.padding(top = 15.dp))
                }
            }
        }
    }
}