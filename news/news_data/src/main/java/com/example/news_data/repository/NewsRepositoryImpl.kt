package com.example.news_data.repository

import com.example.news_data.mapper.toDomainArticle
import com.example.news_data.network.NewsAPIService
import com.example.news_data.room.NewsDao
import com.example.news_domain.model.Article
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
}