package com.example.newsapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.core_ui.*
import com.example.news_presentation.composables.NewsItemScreen
import com.example.newsapp.navigation.Route
import com.example.newsapp.navigation.Screen
import com.example.newsapp.navigation.SetUpNavGraph
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.onboarding_presentation.composables.OnBoardingScreen
import com.example.onboarding_presentation.properties.OnBoardingProperties
import com.example.onboarding_presentation.state.descriptionList
import com.example.onboarding_presentation.state.imageIdList
import com.example.onboarding_presentation.state.titleList
import com.example.search_presentation.composable.ScrollableAppBar
import com.example.search_presentation.composable.TopSearchBar
import com.example.search_presentation.composable.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
            val searchViewModel : SearchViewModel by viewModels()
            val scrollState = rememberLazyListState()
            val scrollUpState = searchViewModel.scrollUp.observeAsState()
//

                searchViewModel.updateScrollPosition(scrollState.firstVisibleItemIndex)
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = if (true) {
                            Route.ON_SPLASH
                        } else Route.BLOG_OVERVIEW
                    ) {
                        composable(route = Route.ON_SPLASH) {
                            AnimatedSplashScreen(navController, isLottieFinished = {
                                navController.navigate(Route.ON_BOARDING)
                            })
                        }
                        composable(Route.ON_BOARDING) {
                            OnBoardingScreen(
                                imageIdList = imageIdList,
                                navController = navController,
                                lifecycleCoroutineScope = lifecycleScope,
                                titleList = titleList,
                                descriptionList = descriptionList,
                                skipTo = Route.ON_NEWS,
                                properties = OnBoardingProperties(
                                    buttonColor = DarkGray,
                                    selectedDotColor = Purple,
                                    imageContentScale = ContentScale.Crop,
                                    titleFontSize = 24.sp,
                                    descriptionFontSize = 16.sp,
                                    titleFontFamily = FontFamily.Default,
                                    descriptionFontFamily = FontFamily.Default,
                                    skipButtonName = stringResource(id = com.example.core_ui.R.string.skip),
                                    nextButtonName = stringResource(id = com.example.core_ui.R.string.next)
                                )
                            )
                        }

                        composable(Route.ON_NEWS){
                            Box() {
                                NewsItemScreen(scrollState)
                                ScrollableAppBar(scrollUpState = scrollUpState, title = "News Headlines", viewModel = searchViewModel)
                        }
                    }
                }
            }

//                var title by remember {
//                    mutableStateOf("")
//                }
//                TopSearchBar(
//                    title,
//                    label = "Search",
//                    onTextChangeListener
//                        = {
//                        if (it.all { char ->
//                                char.isLetter() || char.isWhitespace()
//                            }) title = it
//                    },
//                    modifier = Modifier
//                        .border(
//                            BorderStroke(width = 2.dp, color = Color.Black),
//                            shape = RoundedCornerShape(50)
//                        )
//                        .padding(horizontal = 5.dp)
//                        .fillMaxWidth(),
//                    isSingleLine = true,
//                    onImeAction = {
//
//                    })

            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    Scaffold(topBar = { TopAppBar(title = { Text(text = "News Headlines") }) }) {
        NewsHeadlineItems()
    }
}

@Composable
fun NewsHeadlineItems() {

}

@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppTheme {
        TopSearchBar(
            "",
            label = "Search",
            onTextChangeListener = {

            },
            modifier = Modifier
                .border(
                    BorderStroke(width = 2.dp, color = Color.Black),
                    shape = RoundedCornerShape(50.dp)
                )
                .padding(20.dp),
            isSingleLine = true,
            onImeAction = {

            })

    }
}

/*
OnBoardingScreen(
imageIdList = imageIdList,
navController = navController,
lifecycleCoroutineScope = lifecycleScope,
titleList = titleList,
descriptionList = descriptionList,
skipTo = Route.BLOG_OVERVIEW,
properties = OnBoardingProperties(
buttonColor = DarkGray,
selectedDotColor = Purple,
imageContentScale = ContentScale.Crop,
titleFontSize = 24.sp,
descriptionFontSize = 16.sp,
titleFontFamily = FontFamily.Default,
descriptionFontFamily = FontFamily.Default,
skipButtonName = stringResource(id = com.example.core_ui.R.string.skip),
nextButtonName = stringResource(id = com.example.core_ui.R.string.next)
)
)*/
