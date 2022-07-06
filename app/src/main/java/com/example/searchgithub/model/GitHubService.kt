package com.example.searchgithub.model

import com.example.searchgithub.DataModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitHubService {
    @Headers("Content-Type: application/json",
    "Connection: keep-alive")
    @GET("/search/users")
    fun getGitHubRepository(@Query("q") q: String,
                      @Query("page") page: Int,
                      @Query("per_page") per_page: Int
    ): Single<GitHubResponse>
}