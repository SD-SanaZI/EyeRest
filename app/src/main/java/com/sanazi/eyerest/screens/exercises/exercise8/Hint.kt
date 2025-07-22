package com.sanazi.eyerest.screens.exercises.exercise8

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sanazi.eyerest.R

@Composable
fun Hint(
    modifier: Modifier = Modifier,
){
    val state = remember { mutableStateOf(true) }
    Box(
        modifier.fillMaxWidth().height(180.dp),
        contentAlignment = Alignment.Center
    ) {
        if (state.value) {
            WTFButton { state.value = state.value.not() }
        } else {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.wtf_text),
                    Modifier.fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
                        .padding(18.dp, 14.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
                Text(
                    stringResource(R.string.ok_str).uppercase(),
                    Modifier.width(88.dp).height(29.dp)
                        .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(7.dp))
                        .padding(30.dp, 6.dp)
                        .clickable { state.value = state.value.not() },
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}