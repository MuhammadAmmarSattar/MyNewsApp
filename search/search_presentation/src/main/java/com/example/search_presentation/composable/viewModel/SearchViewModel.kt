package com.example.search_presentation.composable.viewModel

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_ui.Resource
import com.example.core_ui.model.Article
import com.example.search_domain.use_case.GetSearchArticleUseCase
import com.example.search_presentation.composable.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(private val getSearchArticleUseCase : GetSearchArticleUseCase): ViewModel() {

    private val _searchArticles = mutableStateOf(SearchState())
    val searchArticles: State<SearchState> = _searchArticles
    private var lastScrollIndex = 0
    private val _scrollUp = MutableLiveData(false)
    val scrollUp: LiveData<Boolean> get() = _scrollUp


   private  val _searchA = MutableLiveData<List<Article>>()
    val searchA: LiveData<List<Article>> = _searchA

    fun updateSearchList(searchList : List<Article>){
        _searchA.value = searchList
    }

    fun updateScrollPosition(newScrollIndex: Int) {
        if (newScrollIndex == lastScrollIndex) return

        _scrollUp.value = newScrollIndex > lastScrollIndex
        lastScrollIndex = newScrollIndex
    }

    fun searchArticles(query:String) {
        getSearchArticleUseCase(query).onEach {
            when (it) {
                is Resource.Loading -> {
                    _searchArticles.value = SearchState(isLoading = true)
                }
                is Resource.Error -> {
                    _searchArticles.value = SearchState(error = it.message)
                }
                is Resource.Success -> {
                    _searchArticles.value = SearchState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}