package com.example.search_presentation.composable.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core_ui.Resource
import com.example.search_domain.use_case.GetSearchArticleUseCase
import com.example.search_presentation.composable.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(private val getSearchArticleUseCase : GetSearchArticleUseCase): ViewModel() {

    private val _searchArticles = MutableStateFlow(SearchState())
    val searchArticles : StateFlow<SearchState> = _searchArticles

    private var lastScrollIndex = 0

    private val _scrollUp = MutableLiveData(false)
    val scrollUp: LiveData<Boolean>
        get() = _scrollUp
    fun updateScrollPosition(newScrollIndex: Int) {
        if (newScrollIndex == lastScrollIndex) return

        _scrollUp.value = newScrollIndex > lastScrollIndex
        lastScrollIndex = newScrollIndex
    }

    init {
        searchArticles("apple")
    }
    fun searchArticles(query:String){
        getSearchArticleUseCase(query).onEach {

            when(it){
                is Resource.Loading->{
                    _searchArticles.value = SearchState(isLoading = true)
                }
                is Resource.Success->{
                    _searchArticles.value = SearchState(data = it.data)
                }
                is Resource.Error->{
                    _searchArticles.value = SearchState(error = it.message)
                }
            }
        }
    }
}