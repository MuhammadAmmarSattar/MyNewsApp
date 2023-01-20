package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.core_ui.DarkGray
import com.example.core_ui.Purple
import com.example.newsapp.navigation.Route
import com.example.newsapp.navigation.Screen
import com.example.newsapp.navigation.SetUpNavGraph
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.onboarding_presentation.composables.OnBoardingScreen
import com.example.onboarding_presentation.properties.OnBoardingProperties
import com.example.onboarding_presentation.state.descriptionList
import com.example.onboarding_presentation.state.imageIdList
import com.example.onboarding_presentation.state.titleList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
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
                        composable(route = Route.ON_SPLASH){
                            AnimatedSplashScreen(navController , isLottieFinished = {
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
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Android $name")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppTheme {
        Greeting("Android")
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
