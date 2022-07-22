package com.example.searchgithub.di

import com.example.searchgithub.model.Repository
import com.example.searchgithub.model.RepositoryImpl
import com.example.searchgithub.model.service.GitHubService
import com.example.searchgithub.ui.MainAdapter
import com.example.searchgithub.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.github.com"

var apiDevPart = module {
    single<GitHubService>{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubService::class.java)
    }
}
//생성자에 의해 의존성 주입받는 형태
var modelPart = module {
    factory<Repository> {
        RepositoryImpl(get())
    }
}
var viewModelPart = module{
    viewModel{
        MainViewModel(get())
    }
}
var adapterPart = module{
    factory{
        MainAdapter()
    }
}


var myDiModule = listOf(
    apiDevPart,
    modelPart,
    viewModelPart,
    adapterPart
)