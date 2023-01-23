package com.example.search_data.repository

import com.example.news_data.mapper.toDomainArticle
import com.example.search_data.network.SearchAPi
import com.example.search_domain.model.Article
import com.example.search_domain.repository.SearchRepository

class SearchResponseImpl(private val searchAPi: SearchAPi):SearchRepository {
    override suspend fun getSearchArticle(map: MutableMap<String, String>): List<Article> {
        return searchAPi.getSearchArticles(map).articles.map { it.toDomainArticle() }
    }
}