package com.example.searchgithub.model.service

import com.example.searchgithub.model.GitHubResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitHubService {
    @Headers("Content-Type: application/json",
    "Connection: keep-alive")
    @GET("/search/repositories")
    fun getGitHubRepository(
        @Header("Authorization") token: String?,
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Single<GitHubResponse>
}