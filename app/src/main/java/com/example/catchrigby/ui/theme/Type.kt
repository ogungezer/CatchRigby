package com.example.catchrigby.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.catchrigby.R


val bangerRegular = FontFamily(
    Font(R.font.bangers_regular, FontWeight.Bold)
)

val robotoFamily = FontFamily(
    fonts = listOf(
    Font(R.font.robotomono_regular, FontWeight.Normal),
    Font(R.font.robotomono_thin, FontWeight.Thin)
    )
)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val bangerStyle = TextStyle(
    fontFamily = bangerRegular,
    fontSize = 30.sp
)

val robotoStyle = TextStyle(
    fontFamily = robotoFamily,
    letterSpacing = 0.5.sp
)