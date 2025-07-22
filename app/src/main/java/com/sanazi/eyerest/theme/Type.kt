package com.sanazi.eyerest.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.sanazi.eyerest.R

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.k2d_extra_bold)),
        fontWeight = FontWeight(800),
        fontSize = 96.sp,
        lineHeight = 23.sp,
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.k2d_extra_bold)),
        fontWeight = FontWeight(800),
        fontSize = 15.sp,
        lineHeight = 23.sp,
        color = Color.White,
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.k2d_semi_bold)),
        fontWeight = FontWeight(600),
        fontSize = 10.sp,
        lineHeight = 23.sp,
        color = Color.Black,
    ),
    bodyLarge = TextStyle(
    fontFamily = FontFamily(Font(R.font.k2d_extra_bold)),
    fontWeight = FontWeight(800),
    fontSize = 16.sp,
    lineHeight = 23.sp,
    color = Color.White,
),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.k2d_light)),
        fontWeight = FontWeight(300),
        fontSize = 16.sp,
        lineHeight = 23.sp,
        color = Color.White,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.k2d_light)),
        fontWeight = FontWeight(300),
        fontSize = 15.sp,
        lineHeight = 23.sp,
        color = Color(0xFF262626),
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.krona_one_regular)),
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        lineHeight = 23.sp,
        color = Color.White,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.k2d_extra_bold)),
        fontWeight = FontWeight(800),
        fontSize = 13.sp,
        lineHeight = 13.sp,
        color = Color.White,
        textAlign = TextAlign.Right
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.k2d_extra_bold)),
        fontWeight = FontWeight(800),
        fontSize = 18.sp,
        lineHeight = 23.sp,
        color = Color(0xFFFFD91C),
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.k2d_extra_bold)),
        fontWeight = FontWeight(800),
        fontSize = 11.sp,
        lineHeight = 15.sp,
        textAlign = TextAlign.Justify,
        color = Color(0xFF262626),
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.k2d_extra_bold)),
        fontWeight = FontWeight(800),
        fontSize = 11.sp,
        lineHeight = 13.sp,
        color = Color(0xFF262626),
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.k2d_extra_bold)),
        fontWeight = FontWeight(800),
        fontSize = 24.sp,
        lineHeight = 23.sp,
        color = Color(0xFFFFD91C),
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)


val TimePickerTypography = Typography(
    displayLarge = Typography().displayLarge.copy(
        fontFamily = FontFamily(Font(R.font.k2d_extra_bold)),
        fontWeight = FontWeight(800)
    ),
    bodyLarge = Typography().bodyLarge.copy(
        fontFamily = FontFamily(Font(R.font.k2d_extra_bold)),
        fontWeight = FontWeight(800),
    )
)