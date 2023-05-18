package com.example.newsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.newsapp.navigation.Screen


@Composable
fun AnimatedSplashScreen(navController: NavHostController ,isLottieFinished : (()-> Unit)? =null){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))
        val logoAnimationState =
            animateLottieCompositionAsState(composition = composition)
        LottieAnimation(
            composition = composition,
            progress = { logoAnimationState.progress }
        )
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
            isLottieFinished?.invoke()
//            navController.navigate(Screen.Home.route)
        }
    }

}