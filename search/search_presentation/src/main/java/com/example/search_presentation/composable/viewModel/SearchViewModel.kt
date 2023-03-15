package com.example.search_presentation.composable.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(): ViewModel() {
    private var lastScrollIndex = 0

    private val _scrollUp = MutableLiveData(false)
    val scrollUp: LiveData<Boolean>
        get() = _scrollUp
    fun updateScrollPosition(newScrollIndex: Int) {
        if (newScrollIndex == lastScrollIndex) return

        _scrollUp.value = newScrollIndex > lastScrollIndex
        lastScrollIndex = newScrollIndex
    }

}