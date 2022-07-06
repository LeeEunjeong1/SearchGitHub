package com.example.searchgithub.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchgithub.DataModel
import com.example.searchgithub.model.DataModelImpl
import com.example.searchgithub.model.GitHubResponse
import io.reactivex.schedulers.Schedulers

class  MainViewModel(private  val model: DataModel) : ViewModel() {
    private val _getGitHubRepository = MutableLiveData<GitHubResponse>()

    val getDataRepository: LiveData<GitHubResponse>
        get() = _getGitHubRepository

    private val _isError = MutableLiveData<String>()
    val isError: LiveData<String> get() = _isError

    fun getGitHub(q:String, page:Int) {
        try{
            model.getGitHubSearch(q,page)
                .subscribeOn(Schedulers.io()) // [첫번째 스트림 ~ observeOn 호출 전 까지의 스트림]의 쓰레드를 지정
                .subscribe({
                    it.run {
                        Log.d( "MainViewModel","spells : $it")
                        _getGitHubRepository.postValue(it)
                    }
                }, {
                    _isError.postValue(it.message)
                    Log.d("MainViewModel", "response error, message : ${it.message}")
                })
        }catch (e:Exception){
            _isError.postValue(e.message)
        }

    }
}