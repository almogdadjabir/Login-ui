package com.almogdadjabir.com

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import coil.annotation.ExperimentalCoilApi
import com.almogdadjabir.com.view.*
import com.almogdadjabir.com.view.welcome.WelcomeScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.composable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    @OptIn(ExperimentalAnimationApi::class)
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navHostController = rememberAnimatedNavController()

            AnimatedNavHost(
                navController = navHostController,
                startDestination = "Home",
                //modifier = Modifier.background(Color(0xfff9f2ec))
            ) {
                composable(
                    "SingIn",
                    exitTransition = {_,_ ->
                        slideOutHorizontally(
                            targetOffsetX = {-300},
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            )

                        )+ fadeOut(animationSpec = tween(300))
                    },
                    popEnterTransition = {_,_ ->
                        slideInHorizontally(
                            initialOffsetX = {-300},
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            )

                        ) +fadeIn(animationSpec = tween(300))
                    }
                    ) {
                    SingIn(navHostController)
                }
                composable(
                    "Home",
                            exitTransition = {_,_ ->
                        slideOutHorizontally(
                            targetOffsetX = {-300},
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            )

                        )+ fadeOut(animationSpec = tween(300))
                    },
                    popEnterTransition = {_,_ ->
                        slideInHorizontally(
                            initialOffsetX = {-300},
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            )

                        ) +fadeIn(animationSpec = tween(300))
                    }) {
                    Home()
                }
                composable(
                    "WelcomeScreen",
                            exitTransition = {_,_ ->
                        slideOutHorizontally(
                            targetOffsetX = {-300},
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            )

                        )+ fadeOut(animationSpec = tween(300))
                    },
                    popEnterTransition = {_,_ ->
                        slideInHorizontally(
                            initialOffsetX = {-300},
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            )

                        ) +fadeIn(animationSpec = tween(300))
                    }) {
                    WelcomeScreen(navHostController)
                }
                composable(
                    "SingUp",
                            exitTransition = {_,_ ->
                        slideOutHorizontally(
                            targetOffsetX = {-300},
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            )

                        )+ fadeOut(animationSpec = tween(300))
                    },
                    popEnterTransition = {_,_ ->
                        slideInHorizontally(
                            initialOffsetX = {-300},
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            )

                        ) +fadeIn(animationSpec = tween(300))
                    }) {
                    SingUp(navHostController)
                }
            }
        }
    }
}