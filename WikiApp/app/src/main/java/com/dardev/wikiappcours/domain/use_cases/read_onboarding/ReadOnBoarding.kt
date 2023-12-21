package com.dardev.wikiappcours.domain.use_cases.read_onboarding

import com.dardev.wikiappcours.data.repository.WikiRepository
import kotlinx.coroutines.flow.Flow

class ReadOnBoarding(
    private val repository: WikiRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}