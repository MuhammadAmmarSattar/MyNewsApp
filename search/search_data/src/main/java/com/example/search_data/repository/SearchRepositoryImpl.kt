package com.example.search_data.repository

import com.example.core_ui.model.Article
import com.example.search_data.mapper.toDomainArticle
import com.example.search_data.network.SearchAPI
import com.example.search_domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchAPI: SearchAPI) :
    SearchRepository {


        override suspend fun getSearchArticles(query:String): List<Article> {
            return try {
                 searchAPI.getSearchArticles(query).articles.map { it.toDomainArticle() }
            }catch (e:Exception){
                emptyList<Article>()
            }
        }


}