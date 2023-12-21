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