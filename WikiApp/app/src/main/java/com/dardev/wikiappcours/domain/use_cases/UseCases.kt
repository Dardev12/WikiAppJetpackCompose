package com.dardev.wikiappcours.domain.use_cases

import com.dardev.wikiappcours.domain.use_cases.read_onboarding.ReadOnBoarding
import com.dardev.wikiappcours.domain.use_cases.save_onboarding.SaveOnBoarding

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoarding,
    val readOnBoardingUseCase: ReadOnBoarding
)
