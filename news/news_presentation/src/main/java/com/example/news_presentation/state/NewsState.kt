package com.example.news_presentation.state

import com.example.core_ui.Resource
import com.example.core_ui.model.Article

data class NewsState(
    val loading: Boolean = false,
    val error: String = "",
    val data: List<Article>? = null
)
