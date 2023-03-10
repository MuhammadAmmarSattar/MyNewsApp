package com.example.search_data.model

data class ArticleDTO(
    val author: String?=null,
    val content: String?=null,
    val description: String?=null,
    val publishedAt: String?=null,
    val source: SourceDTO,
    val title: String?=null,
    val url: String?=null,
    val urlToImage: String?=null
)