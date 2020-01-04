package com.kilani.nowornever.di

import com.kilani.nowornever.data.repository.ChallengeRepository
import com.kilani.nowornever.data.repository.UserRepository
import com.kilani.nowornever.ui.homechallenges.current.send.SendChallengeViewModel
import com.kilani.nowornever.ui.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val noworneverKoinModule = module("com.kilani.nowornever", createOnStart = true, override = true) {


    //Repositories

    single { UserRepository() }
    single { ChallengeRepository() }

    //ViewModels
    viewModel { MainViewModel(get(), get()) }
    viewModel { SendChallengeViewModel(get()) }
}