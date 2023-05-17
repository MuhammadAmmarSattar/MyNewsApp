package com.example.search_presentation.composable

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news_presentation.NewsViewModel
import com.example.search_presentation.composable.viewModel.SearchViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScrollableAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    background: Color = MaterialTheme.colors.primary,
    scrollUpState: State<Boolean?>,
    viewModel: SearchViewModel,
    viewModels: NewsViewModel = hiltViewModel()

) {
    val position by animateFloatAsState(if (scrollUpState.value == true) -150f else 0f)
    var isSearchClick  by remember {
        mutableStateOf(true)
    }
    val res1 = viewModels.searchFlow.value
    if (res1.loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    if (res1.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            Text(text = res.error)
        }
    }
    res1.data?.let { articleList ->
        Log.e(TAG, "pgl=> ${articleList.size}" )
    }
    Surface(modifier = Modifier.graphicsLayer { translationY = (position) }, elevation = 8.dp) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(color = background),
        ) {
            Row(modifier = modifier
                .fillMaxHeight()
                .padding(start = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                if (navigationIcon != null) {
                    navigationIcon()
                }
                if (isSearchClick){
                    Text(text = "News App"  , style = MaterialTheme.typography.h2 , textAlign = TextAlign.Center,
                        color = Color.White, fontWeight = FontWeight.ExtraBold , fontSize = 18.sp)
                }else{
                    var title by remember {
                        mutableStateOf("")
                    }
                    TopSearchBar(title,
                        label = "Search",
                        onTextChangeListener
                        = {
                            if (it.all { char -> char.isLetter() || char.isWhitespace()
                                }) title = it
                        },
                        isSingleLine = true,
                        onImeAction = {
                            viewModels.search()

                        })
                }
            }
            Row(modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp)
                .align(Alignment.CenterEnd), verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = {         isSearchClick = !isSearchClick}
                ) {
                    if (isSearchClick){
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "contentDescription", tint = Color.White
                        )
                    }else {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "closeIcon", tint = Color.White
                        )
                    }
                }
            }
        }
    }
}


