package com.example.todo.commonComponents


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@Composable
fun LottieAnimationView(animationResId: Int,size:Int,padding:Int) {

    var isPlaying by remember { mutableStateOf(true) }

    val speed by remember { mutableStateOf(1f) }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(animationResId)
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = false

    )

    LottieAnimation(
        composition,
        progress,
        modifier = Modifier.padding(top = padding.dp).size(size.dp),
    )


}
