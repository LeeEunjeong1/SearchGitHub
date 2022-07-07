package com.example.searchgithub.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchgithub.model.DataModel
import com.example.searchgithub.model.RepositoryModel
import io.reactivex.rxjava3.schedulers.Schedulers

class  MainViewModel(private  val model: DataModel) : ViewModel() {

    private val _getGitHubRepository = MutableLiveData<List<RepositoryModel>>()

    val getDataRepository: LiveData<List<RepositoryModel>>
        get() = _getGitHubRepository

    private val _isError = MutableLiveData<String>()
    val isError: LiveData<String> get() = _isError

    // github repository 가져오기
    fun getGitHub(q:String, page:Int) {
        val list : MutableList<RepositoryModel> = mutableListOf()
        try{
            model.getGitHubSearch(q,page)
                .subscribeOn(Schedulers.io()) // [첫번째 스트림 ~ observeOn 호출 전 까지의 스트림]의 쓰레드를 지정
                .subscribe({
                    it.run {
                        Log.d("Mainvviewlsize",it.items.size.toString())
                        Log.d( "MainViewModel","$it")
                        for(i:Int in 0 until it.items.size){
                            list.add(
                                RepositoryModel(
                                    it.items[i].owner.avatar_url,
                                    it.items[i].full_name,
                                    it.items[i].language,
                                )
                            )

                        }
                        // avatar_url, full_name, language 리스트 생성 후 보내기
                        _getGitHubRepository.postValue(list)

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