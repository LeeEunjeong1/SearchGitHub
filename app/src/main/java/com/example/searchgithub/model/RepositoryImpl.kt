package com.example.searchgithub.model

import com.example.searchgithub.model.service.GitHubService
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(private val service: GitHubService) : Repository {
    companion object{
        const val TOKEN = "token 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"
        const val DEFAULT_PER_PAGE = 10
    }
    override fun getGitHubSearch(q:String, page:Int): Single<GitHubResponse> {
        return service.getGitHubRepository(TOKEN,q,page,DEFAULT_PER_PAGE)
    }

}