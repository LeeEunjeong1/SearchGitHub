package com.example.searchgithub

import com.example.searchgithub.model.GitHubResponse
import io.reactivex.Single

interface DataModel{
    fun getGitHubSearch(q:String, page:Int) : Single<GitHubResponse>
}