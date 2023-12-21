package com.dardev.wikiappcours.domain.use_cases.save_onboarding

import com.dardev.wikiappcours.data.repository.WikiRepository

class SaveOnBoarding(
    private val repository: WikiRepository
) {
    suspend operator fun invoke(completed:Boolean) {
        repository.saveOnBoardingState(completed)
    }
}