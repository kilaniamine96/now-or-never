package com.kilani.nowornever.di

import com.kilani.nowornever.data.UserRepository
import com.kilani.nowornever.ui.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val noworneverKoinModule = module("com.kilani.nowornever", createOnStart = true, override = true) {


    //Repositories

    single { UserRepository() }

    //ViewModels
    viewModel { MainViewModel(get()) }
}