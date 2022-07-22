package com.example.searchgithub.model

import io.reactivex.rxjava3.core.Single

interface Repository{
    fun getGitHubSearch(q:String, page:Int) : Single<GitHubResponse>
}