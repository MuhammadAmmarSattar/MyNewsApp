package com.example.news_domain.repository

import com.example.core_ui.model.Article

interface NewsRepository {

    suspend fun getNewsArticle():List<Article>

    suspend fun getSearchArticles(query:String):List<Article>



}