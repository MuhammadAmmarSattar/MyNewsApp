package com.example.search_domain.use_case

import com.example.core_ui.Resource
import com.example.search_domain.model.Article
import com.example.search_domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSearchArticleUseCase(private val searchRepository: SearchRepository) {

    operator fun invoke(map: MutableMap<String, String>): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data=searchRepository.getSearchArticle(map)))
        }catch (e:java.lang.Exception){
            emit(Resource.Error(e.message.toString()))
            e.printStackTrace()
        }
    }
}