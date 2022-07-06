package com.example.searchgithub.di

import com.example.searchgithub.DataModel
import com.example.searchgithub.model.DataModelImpl
import com.example.searchgithub.model.GitHubService
import com.example.searchgithub.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://api.github.com"

var apiDevPart = module {
    single<GitHubService>{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubService::class.java)
    }
}
//생성자에 의해 의존성 주입받는 형태
var modelPart = module {
    factory<DataModel> {
        DataModelImpl(get())
    }
}
var viewModelPart = module{
    viewModel{
        MainViewModel(get())
    }
}

var myDiModule = listOf(
    apiDevPart,
    modelPart,
    viewModelPart
)