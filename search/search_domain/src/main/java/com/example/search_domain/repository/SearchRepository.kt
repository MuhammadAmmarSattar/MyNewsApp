package com.example.search_domain.repository

import com.example.core_ui.model.Article

interface SearchRepository {

    suspend fun getSearchArticles(query:String):List<Article>
}