package com.example.a212029_alyadamia_drnazatul_lab3.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.a212029_alyadamia_drnazatul_lab3.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.a212029_alyadamia_drnazatul_lab3.ui.theme.Typography

val AbrilFatface = FontFamily(
    Font(R.font.abril_fatface_regular)
)
val BricolageGrotesque = FontFamily(
    Font(R.font.bricolage_grotesque)
)



val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = AbrilFatface,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    displayMedium = TextStyle(
        fontFamily = BricolageGrotesque,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = AbrilFatface,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = BricolageGrotesque,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)

