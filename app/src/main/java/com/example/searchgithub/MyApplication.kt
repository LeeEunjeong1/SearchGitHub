package com.example.searchgithub
import android.app.Application
import com.example.searchgithub.di.myDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //모듈 등록
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE) //koin인이 로그를 남기는 레벨 지정
            androidContext(this@MyApplication) // Context 주입
            modules(myDiModule) //선언한 모듈 지정
        }
    }
}