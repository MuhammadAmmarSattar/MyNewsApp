package com.example.search_domain.use_case

import android.util.Log
import com.example.core_ui.Resource
import com.example.core_ui.model.Article
import com.example.search_domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchArticleUseCase @Inject constructor(private val searchRepository: SearchRepository) {


    operator fun invoke(query:String):Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data=searchRepository.getSearchArticles(query)))
        }catch (e:Exception){
            Log.e("Failed=>","Failed")
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}