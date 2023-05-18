package com.example.news_domain.use_case

import com.example.core_ui.Resource
import com.example.core_ui.model.Article
import com.example.news_domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchArticleUseCases @Inject constructor(private val newsRepository: NewsRepository) {

    operator fun invoke(query:String): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
        emit(Resource.Success(newsRepository.getSearchArticles(query)))
        }catch (e:java.lang.Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}