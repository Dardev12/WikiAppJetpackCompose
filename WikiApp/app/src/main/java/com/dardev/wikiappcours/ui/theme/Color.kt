package com.dardev.wikiappcours.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightModeP = Color(0xFFF7C04A)
val NightModeP = Color(0xFFFAAD06)
val LightModePV = Color(0xFFF7E2B6)
val NightModePV = Color(0xFF000000)
val optionColor = Color(0xFF707070)
val white = Color(0xFFFFFFFF)

// Type Character
val Roundtable = Color(0xFFA75C05)
val Limgrave = Color(0xFF5D9C59)
val Caria = Color(0xFF3358A2)
val Caelid = Color(0xFF9C254D)
val Leyndell = Color(0xFFD5B321)
val Volcano = Color(0xFFC60026)
val FireGiant = Color(0xFFB4CDE6)
val FarumAzul = Color(0xFFAA9D8A)
val otherArea = Color(0xFF187774)

//
val Colors.welcomeScreenBackgroundColor
    @Composable
    get() = if (isLight) LightModeP else NightModeP

val Colors.titleColor
    @Composable
    get() = if (isLight) NightModePV else LightModePV

val Colors.descriptionColor
    @Composable
    get() = if (isLight) NightModePV.copy(alpha = 0.5f)
    else LightModePV.copy(alpha = 0.5f)

val Colors.buttonBackgroundColor
    @Composable
    get() = if (isLight) LightModePV else NightModePV

val Colors.buttonTextColor
    @Composable
    get() = if (isLight) NightModePV else LightModePV

val Colors.statusBarColor
    @Composable
    get() = if (isLight) LightModePV else NightModeP

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) LightModeP  else NightModeP

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) LightModePV  else NightModePV

val Colors.TitleItemColor:Color
    @Composable
    get() = if (isLight) LightModePV  else NightModeP