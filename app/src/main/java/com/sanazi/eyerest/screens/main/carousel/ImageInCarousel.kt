package com.sanazi.eyerest.screens.main.carousel

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sanazi.eyerest.R

@Composable
fun ImageInCarousel(
    news: News,
    modifier: Modifier = Modifier
){
    val shape = remember { RoundedCornerShape(25.dp) }
    Box(
        modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(shape)
            .border(3.dp, MaterialTheme.colorScheme.outline, shape)
    ) {
        Image(
            painterResource(news.shortImage), null,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(
            stringResource(news.shortText),
            Modifier.align(Alignment.TopEnd).padding(17.dp).width(100.dp).height(30.dp),
            style = MaterialTheme.typography.titleSmall)
        Image(
            painterResource(R.drawable.underline), null,
            Modifier.align(Alignment.TopEnd).padding(top =  49.dp)
                .scale(-1f,1f)
                .clip(AbsoluteCutCornerShape(0.dp))
                .offset(x = (-46).dp)
                .width(118.dp).height(11.dp),
        )
        MoreButtonInCarousel(Modifier.align(Alignment.BottomEnd).padding(20.dp,12.dp))
    }
}