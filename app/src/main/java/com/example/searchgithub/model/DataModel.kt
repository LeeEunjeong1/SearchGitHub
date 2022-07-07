package com.example.searchgithub.model

import io.reactivex.rxjava3.core.Single

interface DataModel{
    fun getGitHubSearch(q:String, page:Int) : Single<GitHubResponse>
}