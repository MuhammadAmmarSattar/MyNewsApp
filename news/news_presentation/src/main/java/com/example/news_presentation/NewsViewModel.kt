package com.example.news_presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_ui.Resource
import com.example.news_domain.use_case.GetNewsArticleUseCase
import com.example.news_presentation.state.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsViewModel  @Inject constructor(private val getNewsArticleUseCase: GetNewsArticleUseCase):ViewModel() {


    private val _newsStateFlow = mutableStateOf(NewsState())
    val newsStateFlow : State<NewsState> = _newsStateFlow

    init {
        getNewsArticles()
    }

    fun getNewsArticles() {
        getNewsArticleUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _newsStateFlow.value = NewsState(loading  = true)
                }
                is Resource.Error -> {
                    _newsStateFlow.value = NewsState(error = it.message)
                }
                is Resource.Success -> {
                    _newsStateFlow.value = NewsState(data = it.data)
                }
            }


        }.launchIn(viewModelScope)
    }

}