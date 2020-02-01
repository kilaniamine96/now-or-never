package com.kilani.nowornever.di

import androidx.room.Room
import com.kilani.nowornever.data.database.NowOrNeverDatabase
import com.kilani.nowornever.data.repository.ChallengeRepository
import com.kilani.nowornever.data.repository.UserRepository
import com.kilani.nowornever.ui.homechallenges.current.send.SendChallengeViewModel
import com.kilani.nowornever.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val noworneverKoinModule = module("com.kilani.nowornever", createOnStart = true, override = true) {


    //Repositories

    single { UserRepository(get()) }
    single { ChallengeRepository() }

    single { Room.databaseBuilder(androidContext(), NowOrNeverDatabase::class.java,"nowornever-db").fallbackToDestructiveMigration().build() }
    single { (get() as NowOrNeverDatabase).usersDao() }
    //ViewModels
    viewModel { MainViewModel(get(), get()) }
    viewModel { SendChallengeViewModel(get()) }
}