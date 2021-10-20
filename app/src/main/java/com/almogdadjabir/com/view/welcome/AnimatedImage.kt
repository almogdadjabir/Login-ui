package com.almogdadjabir.com.view.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.almogdadjabir.com.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedImage(visible: Boolean) {
    val imageEnterTransition = fadeIn(
        animationSpec = tween(2000)
    )

    AnimatedVisibility(
        visible = visible,
        enter = imageEnterTransition,
    ) {
        Image(
            painter = painterResource(R.drawable.fortest),
            contentDescription = "",
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth()
        )
    }
}