package com.example.search_domain.repository

import com.example.search_domain.model.Article

interface SearchRepository {

    suspend fun getSearchArticles(query:String):List<Article>
}