package com.dardev.wikiappcours.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dardev.wikiappcours.R

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
    object First: OnBoardingPage(
        tag = 1,
        image = R.drawable.gretting,
        title = R.string.on_boarding_title_one,
        description = R.string.on_boarding_one,
        goTo = 2
    )

    object Second: OnBoardingPage(
        tag = 2,
        image = R.drawable.explore,
        title = R.string.on_boarding_title_two,
        description = R.string.on_boarding_two,
        goTo = 3
    )

    object Third: OnBoardingPage(
        tag = 3,
        image = R.drawable.power,
        title = R.string.on_boarding_title_three,
        description = R.string.on_boarding_three,
        goTo = 0
    )
}