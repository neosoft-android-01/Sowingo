package com.neosofttech.sowingo.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.neosofttech.sowingo.R

val SFProFont = FontFamily(
    Font(R.font.sf_pro_text_bold,FontWeight.Bold),
    Font(R.font.sf_pro_text_medium,FontWeight.Medium),
    Font(R.font.sf_pro_text_regular,FontWeight.Normal)
)


val TopBarHeader = TextStyle(
    fontFamily = SFProFont,
    fontWeight = FontWeight(700),
    fontSize = sp_17
)

val SearchHintText = TextStyle(
    fontFamily = SFProFont,
    fontWeight = FontWeight(400),
    fontSize = sp_17,
    color = SearchBarHint
)

val description = TextStyle(
    fontFamily = SFProFont,
    fontWeight = FontWeight(500),
    fontSize = sp_16,
    color = HeaderTextColor
)

val price1 = TextStyle(
    fontFamily = SFProFont,
    fontWeight = FontWeight(700),
    fontSize = sp_18,
    color = HeaderTextColor
)

val price2 = TextStyle(
    fontFamily = SFProFont,
    fontWeight = FontWeight(700),
    fontSize = sp_14,
    color = SearchBarHint
)
// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)