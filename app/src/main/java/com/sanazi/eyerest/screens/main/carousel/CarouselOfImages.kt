package com.sanazi.eyerest.screens.main.carousel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanazi.eyerest.Article
import com.sanazi.eyerest.R

data class News(
    @DrawableRes val shortImage: Int,
    @StringRes val shortText: Int,
    val newsObjects: List<NewsObject>
)

sealed class NewsObject
data class ImageObject(@DrawableRes val image: Int) : NewsObject()
data class TextObject(@StringRes val text: Int) : NewsObject()

val newsList = listOf(
    News(
        R.drawable.article_1_short_image,
        R.string.article_1_short_text,
        listOf(
            ImageObject(R.drawable.article_1_image_1),
            TextObject(R.string.article_1_text_1),
            ImageObject(R.drawable.article_1_image_2),
            TextObject(R.string.article_1_text_2)
        )
    ),
    News(
        R.drawable.article_2_short_image,
        R.string.article_2_short_text,
        listOf(
            ImageObject(R.drawable.article_2_image_1),
            TextObject(R.string.article_2_text_1),
            ImageObject(R.drawable.article_2_image_2),
            TextObject(R.string.article_2_text_2)
        )
    ),
    News(
        R.drawable.article_3_short_image,
        R.string.article_3_short_text,
        listOf(
            ImageObject(R.drawable.article_3_image_1),
            TextObject(R.string.article_3_text_1),
            ImageObject(R.drawable.article_3_image_2),
            TextObject(R.string.article_3_text_2),
            ImageObject(R.drawable.article_3_image_3),
            TextObject(R.string.article_3_text_3),
            ImageObject(R.drawable.article_3_image_4),
            TextObject(R.string.article_3_text_4)
        )
    )
)

@Composable
fun CarouselOfImagesComposable(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val pagerState = rememberPagerState{newsList.count()}
        HorizontalPager(pagerState) { page ->
            ImageInCarousel(newsList[page], Modifier.clickable { navController.navigate(Article(page)) })
        }
        Row(
            Modifier
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            repeat(pagerState.pageCount) { iteration ->
                CarouselOfImagesIndicator(pagerState.currentPage == iteration)
            }
        }
    }
}