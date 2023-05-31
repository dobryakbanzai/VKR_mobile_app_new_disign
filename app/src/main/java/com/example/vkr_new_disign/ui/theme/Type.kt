package com.example.vkr_new_disign.ui.theme


import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.vkr_new_disign.R

val Montserrat = FontFamily(
    Font(R.font.montserrat_black, FontWeight.Black),

    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_extrabold, FontWeight.ExtraBold),

    Font(R.font.montserrat_regular, FontWeight.Normal),

    Font(R.font.montserrat_medium, FontWeight.Medium),

    Font(R.font.montserrat_thin, FontWeight.Thin),

    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_extralight, FontWeight.ExtraLight),





)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    h1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),

    h2 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 35.sp
    ),

    button = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),

//    caption = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 12.sp
//    )



)