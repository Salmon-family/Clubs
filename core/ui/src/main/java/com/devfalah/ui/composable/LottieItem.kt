package com.devfalah.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

@Composable
fun LottieItem(
    modifier: Modifier = Modifier,
    LottieResource: Int
) {
    val context = LocalContext.current
    val customView = remember { LottieAnimationView(context) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidView({ customView }) { view ->
            with(view) {
                setAnimation(LottieResource)
                playAnimation()
                repeatCount = LottieDrawable.INFINITE
                repeatMode = LottieDrawable.RESTART
            }
        }
    }
}