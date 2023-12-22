package com.dardev.wikiappcours.di

import com.dardev.wikiappcours.data.remote.WikiApi
import com.dardev.wikiappcours.data.repository.WikiRepository
import com.dardev.wikiappcours.domain.remote.IWikiApi
import com.dardev.wikiappcours.domain.use_cases.UseCases
import com.dardev.wikiappcours.domain.use_cases.read_onboarding.ReadOnBoarding
import com.dardev.wikiappcours.domain.use_cases.save_onboarding.SaveOnBoarding
import com.dardev.wikiappcours.presentation.screen.detail.DetailViewModel
import com.dardev.wikiappcours.presentation.screen.home.HomeViewModel
import com.dardev.wikiappcours.presentation.screen.splash.SplashViewModel
import com.dardev.wikiappcours.presentation.screen.welcome.WelcomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<IWikiApi> { WikiApi() }
    single { WikiRepository(get(), androidContext()) }

    // UseCase & OnBoarding
    factory { ReadOnBoarding(get()) }
    factory { SaveOnBoarding(get()) }
    factory { UseCases(get(),get()) }

    // ViewModels
    viewModelOf(::SplashViewModel)
    viewModelOf(::WelcomeViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}