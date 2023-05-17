package com.example.search_presentation.composable.state

import com.example.search_domain.model.Article

data class SearchState(
    val isLoading:Boolean=false,
    val error:String="",
    val data:List<Article>?=null
) {
}