package com.example.search_data.repository

import com.example.news_data.mapper.toDomainArticle
import com.example.search_data.network.SearchAPI
import com.example.search_domain.model.Article
import com.example.search_domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchAPI: SearchAPI) :
    SearchRepository {
    override suspend fun getSearchArticles(map: MutableMap<String, String>): List<Article> {
    return searchAPI.getSearchArticles(map).articles.map { it.toDomainArticle()

    }
    }


}