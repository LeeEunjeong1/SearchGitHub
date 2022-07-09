package com.example.searchgithub.model

data class GitHubResponse
    (
    val total_count: Int,
    val items: List<GitHubRepository>
)
data class GitHubRepository(
    val id : Long,
    val full_name : String,
    val owner: GitHubOwner,
    val html_url : String,
    val description: String,
    val stargazers_count: Int,
    val language: String
)
data class GitHubOwner(
    val avatar_url: String
)