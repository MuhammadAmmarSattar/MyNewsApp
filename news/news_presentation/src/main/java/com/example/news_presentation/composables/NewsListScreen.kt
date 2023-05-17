package com.example.news_presentation.composables

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.core_ui.fontSize
import com.example.core_ui.sp15
import com.example.core_ui.spacing
import com.example.news_presentation.NewsViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.State
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.news_domain.model.Article

@Composable
fun NewsItemScreen(scrollState: LazyListState
                   ,viewModel: NewsViewModel = hiltViewModel()) {
    val res = viewModel.newsStateFlow.value
//    Scaffold(topBar = { TopAppBar(title = { Text(text = "News Headlines") }) }) {
        if (res.loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        if (res.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = res.error)
            }
        }
        res.data?.let { articleList ->
            LazyColumn(state = scrollState) {
                items(articleList) {
                    NewsItem(article = it) {
//                        Navigations()
                    }
                }
            }
        }
//    val res1 = viewModel.searchFlow.value
//    if (res1.loading) {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            CircularProgressIndicator()
//        }
//    }
//    if (res1.error.isNotBlank()) {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            Text(text = res.error)
//        }
//    }
//    res1.data?.let { articleList ->
//        Log.e(TAG, "size=> ${articleList.size}" )
//    }
//    }
}

@Composable
fun NewsItem(article: Article, onClickListener: (String) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClickListener.invoke(article.title ?: "ammar")

            }
            .wrapContentHeight()
            .padding(10.dp), shape = RoundedCornerShape(8.dp),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.view_2x),
                style = TextStyle(color = Color.Black, fontSize = sp15)
            )
            Image(
                painter = rememberImagePainter(
                    data = article.urlToImage,
                    builder = {
                        crossfade(true)
                        error(com.example.core_ui.R.drawable.ic_logo)
                        fallback(com.example.core_ui.R.drawable.ic_logo)
                        placeholder(com.example.core_ui.R.drawable.ic_logo)
                    }
                ),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(180.dp)
            )
            Text(
                text = article.description ?:"",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                style = TextStyle(color = Color.Black, fontSize = MaterialTheme.fontSize.view_11x)
            )
        }
    }
}