package com.example.searchgithub.model

import io.reactivex.rxjava3.core.Single

class DataModelImpl(private val service: GitHubService) : DataModel {
    companion object{
        const val DEFAULT_PER_PAGE = 10
    }
    override fun getGitHubSearch(q:String, page:Int): Single<GitHubResponse> {
        return service.getGitHubRepository(q,page,DEFAULT_PER_PAGE)
    }

}