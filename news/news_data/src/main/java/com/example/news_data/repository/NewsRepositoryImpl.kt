package com.example.news_data.repository

import com.example.news_data.mapper.toDomainArticle
import com.example.news_data.network.NewsAPIService
import com.example.news_domain.model.Article
import com.example.news_domain.repository.NewsRepository

class NewsRepositoryImpl(private val newsAPIService: NewsAPIService) : NewsRepository {

    override suspend fun getNewsArticle(): List<Article> {
        return newsAPIService.getNewsList().articles.map { it.toDomainArticle() }
    }
}