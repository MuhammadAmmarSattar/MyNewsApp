package com.example.search_domain.use_case

import com.example.core_ui.Resource
import com.example.search_domain.model.Article
import com.example.search_domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchArticleUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    operator fun invoke(map: MutableMap<String, String>): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
        emit(Resource.Success(searchRepository.getSearchArticles(map)))
        }catch (e:java.lang.Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}