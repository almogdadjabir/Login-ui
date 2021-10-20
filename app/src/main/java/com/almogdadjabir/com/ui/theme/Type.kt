package com.almogdadjabir.com.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.almogdadjabir.com.R

private  val Poppins = FontFamily(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_light,weight = FontWeight.Light),
    Font(R.font.poppins_bold,weight = FontWeight.Bold),
    Font(R.font.poppins_black,weight = FontWeight.Black),
    Font(R.font.poppins_italic,style = FontStyle.Italic),

)

val Kitab = FontFamily(
    Font(R.font.kitab)
)
val uthman = FontFamily(
    Font(R.font.uthman)
)
val Poppins_regular = FontFamily(
    Font(R.font.poppins_regular),
)
// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = Poppins,
    h1 = TextStyle(
        fontFamily = Kitab,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp

    )
)

