package com.dardev.wikiappcours.di

import com.dardev.wikiappcours.data.remote.WikiApi
import com.dardev.wikiappcours.data.repository.WikiRepository
import com.dardev.wikiappcours.domain.remote.IWikiApi
import org.koin.dsl.module

val appModule = module {
    single<IWikiApi> { WikiApi() }
    single { WikiRepository(get()) }

    // UseCase & OnBoarding

    // ViewModels
}