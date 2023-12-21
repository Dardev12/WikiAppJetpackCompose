package com.dardev.wikiappcours.di

import com.dardev.wikiappcours.data.remote.WikiApi
import com.dardev.wikiappcours.data.repository.WikiRepository
import com.dardev.wikiappcours.domain.remote.IWikiApi
import com.dardev.wikiappcours.domain.use_cases.UseCases
import com.dardev.wikiappcours.domain.use_cases.read_onboarding.ReadOnBoarding
import com.dardev.wikiappcours.domain.use_cases.save_onboarding.SaveOnBoarding
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<IWikiApi> { WikiApi() }
    single { WikiRepository(get(), androidContext()) }

    // UseCase & OnBoarding
    factory { ReadOnBoarding(get()) }
    factory { SaveOnBoarding(get()) }
    factory { UseCases(get(),get()) }

    // ViewModels
}