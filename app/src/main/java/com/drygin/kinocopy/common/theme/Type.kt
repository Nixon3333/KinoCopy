package com.drygin.kinocopy.common.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.1.sp
    )
)

val FilmMeta = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        color = CustomColors.DetailsGenre
    )

val RatingValue = TextStyle(
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold,
    letterSpacing = 0.1.sp,
    lineHeight = 28.sp,
    color = ColorPrimary
)

val RatingLabel = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.1.sp,
    lineHeight = 16.sp,
    color = ColorPrimary
)

val FilmDescriptionStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.1.sp,
    color = ColorOnBackground
)