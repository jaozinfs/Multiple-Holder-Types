package com.example.multipleholderrecyclerview

import android.app.Application
import com.example.multipleholderrecyclerview.ui.MainViewModel
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class App : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}