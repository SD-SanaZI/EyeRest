package com.sanazi.eyerest.screens.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanazi.eyerest.screens.elements.alphaScroll
import com.sanazi.eyerest.screens.elements.PopBackRow
import com.sanazi.eyerest.screens.main.carousel.ImageObject
import com.sanazi.eyerest.screens.main.carousel.News
import com.sanazi.eyerest.screens.main.carousel.TextObject

@Composable
fun Article(
    news: News,
    navController: NavController,
    modifier: Modifier = Modifier
) {
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
                .alphaScroll(120f),
            verticalArrangement = Arrangement.spacedBy(23.dp)
        ) {
            Text(
                stringResource(news.shortText),
                Modifier
                    .fillMaxWidth()
                    .height(23.dp),
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            news.newsObjects.forEachIndexed{ index, newsObject ->
                when (newsObject) {
                    is ImageObject -> {
                        Image(
                            painterResource(newsObject.image), null,
                            Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(7.dp)),
                            contentScale = ContentScale.FillWidth
                        )
                    }

                    is TextObject -> {
                        Text(
                            stringResource(newsObject.text),
                            Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }
        }
    }
}