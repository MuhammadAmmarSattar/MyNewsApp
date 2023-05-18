package com.example.news_data.repository

import com.example.news_data.mapper.toDomainArticle
import com.example.news_data.network.NewsAPIService
import com.example.news_data.room.NewsDao
import com.example.core_ui.model.Article
import com.example.news_domain.repository.NewsRepository

class NewsRepositoryImpl(private val newsAPIService: NewsAPIService , private val newsDao: NewsDao) : NewsRepository {
    override suspend fun getNewsArticle(): List<Article> {
        return try {
            var temp =  newsAPIService.getNewsList().articles.map { it.toDomainArticle() }
            newsDao.insertList(temp)
            newsDao.getNewsArticles()
        }catch (e:Exception){
            newsDao.getNewsArticles()
        }
    }

    override suspend fun getSearchArticles(query: String): List<Article> {
        return try {
            newsAPIService.getSearchArticles(query).articles.map { it.toDomainArticle() }
        }catch (e:Exception){
           emptyList<Article>()
        }
    }
}