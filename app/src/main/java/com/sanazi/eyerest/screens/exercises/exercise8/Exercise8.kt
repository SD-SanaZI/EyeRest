package com.sanazi.eyerest.screens.exercises.exercise8

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanazi.eyerest.R
import com.sanazi.eyerest.screens.elements.BorderLine
import com.sanazi.eyerest.screens.elements.ExerciseDescriptionSmall
import com.sanazi.eyerest.screens.elements.ExerciseNumber
import com.sanazi.eyerest.screens.elements.PopBackRow
import com.sanazi.eyerest.screens.elements.Underline

@Composable
fun Exercise8(
    isAutoNext: Boolean,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val list = remember { stereogramList }
    val index = remember { mutableIntStateOf(0) }
    val state = remember { mutableStateOf(Exercise8States.MAIN) }
    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PopBackRow(navController, isAutoNext)
        when(state.value){
            Exercise8States.MAIN -> {
                if (!isAutoNext) {
                    ExerciseNumber(8, Modifier.padding(top = 26.dp))
                    Underline(Modifier.padding(top = 5.dp))
                }
                ExerciseDescriptionSmall(R.string.exercise_8, Modifier.padding(top = 30.dp))
                Image(
                    painterResource(list[index.intValue].shortImage), null,
                    Modifier
                        .padding(vertical = 15.dp)
                        .weight(1f)
                        .aspectRatio(1f)
                        .border(3.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(3.dp)),
                    contentScale = ContentScale.Crop
                )
                BorderLine()
                Row (
                    Modifier.padding(top = 15.dp, bottom = 30.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    FullScreenButton{
                        state.value = Exercise8States.FULL
                    }
                    NextPictureButton{
                        index.intValue++
                        if (index.intValue == list.size) index.intValue = 0
                    }
                }
                Hint()
            }
            Exercise8States.FULL -> {
                Image(
                    painterResource(list[index.intValue].fullImage), null,
                    Modifier
                        .padding(top = 26.dp)
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable { state.value = Exercise8States.MAIN },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

