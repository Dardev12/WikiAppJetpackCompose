package com.dardev.wikiappcours.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class OnBoardingPage(
    val tag:Int,
    @DrawableRes
    val image:Int,
    @StringRes
    val title:Int,
    @StringRes
    val description:Int,
    val goTo:Int
) {
    object First

    object Second

    object Third
}