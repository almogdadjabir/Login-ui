package com.almogdadjabir.com.view.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun WelcomeScreen(
    navHostController: NavHostController,
    ) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        visible = true
    }

    WelcomeScreenContent(visible = visible, navHostController) {
    }
}

@Composable
fun WelcomeScreenContent(
    visible: Boolean,
    navHostController: NavHostController,
    onGetStarted: () -> Unit
) {
    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
                .background(Color(0xFFe7e8e8))
        ) {
            AnimatedTitle(visible = visible)

            AnimatedImage(visible = visible)

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedButton(visible = visible,
                onClick = { navHostController.navigate("Home") }
            )
        }
    }
}
