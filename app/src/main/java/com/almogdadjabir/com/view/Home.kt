package com.almogdadjabir.com.view

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.AbsSeekBar
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.almogdadjabir.com.R
import com.almogdadjabir.com.model.remote.Articles
import com.almogdadjabir.com.other.Constants
import com.almogdadjabir.com.view_model.HomeViewModel
import com.almogdadjabir.com.view_model.ParentViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlin.random.Random


@RequiresApi(Build.VERSION_CODES.M)
@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnrememberedGetBackStackEntry")
@ExperimentalCoilApi
@Composable
fun Home(

) {
    val navController = rememberAnimatedNavController()
    Surface(color = Color.White) {

        // Scaffold Component
        Scaffold(
            // Bottom navigation

            floatingActionButton = {
                //MyFloatingActionButton()

                FloatingActionButton(
                    onClick = {
                        navController.navigate("Player")
                    },
                    backgroundColor = Color.White,
                    content = {
                        val painter = rememberImagePainter(data = "", builder = {
                            transformations(RoundedCornersTransformation(10f))
                            error(R.drawable.ic__884951_app_communication_desktop_earphone_headphone_icon)
                        })
                        Image(painter = painter, contentDescription = "Just Image")
                    }
                )

            },
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center,
            bottomBar = {
                BottomAppBar(
                    // Defaults to null, that is, No cutout
                    cutoutShape = MaterialTheme.shapes.small.copy(
                        CornerSize(percent = 50)
                    )
                )
                {
                    BottomNavigationBar(
                    navController = navController,
               )
                }


            },
                    content = {
                AnimatedNavHost(navController = navController, startDestination = "HomeNews"){
                    composable(
                        "HomeNews",
                        exitTransition = {_,_ ->
                            slideOutHorizontally(
                                targetOffsetX = {-300},
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )

                            ) + fadeOut(animationSpec = tween(300))
                        },
                        popEnterTransition = {_,_ ->
                            slideInHorizontally(
                                initialOffsetX = {-300},
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )

                            ) + fadeIn(animationSpec = tween(300))
                        }
                    ) {
                        HomeNews(navController)
                    }

                    composable(
                        "Details",
                        exitTransition = {_,_ ->
                            slideOutHorizontally(
                                targetOffsetX = {-300},
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )

                            ) + fadeOut(animationSpec = tween(300))
                        },
                        popEnterTransition = {_,_ ->
                            slideInHorizontally(
                                initialOffsetX = {-300},
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )

                            ) + fadeIn(animationSpec = tween(300))
                        }
                    ) {
                        Details(navController)
                    }

                    composable(
                        "Quran",
                        exitTransition = {_,_ ->
                            slideOutHorizontally(
                                targetOffsetX = {-300},
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )

                            ) + fadeOut(animationSpec = tween(300))
                        },
                        popEnterTransition = {_,_ ->
                            slideInHorizontally(
                                initialOffsetX = {-300},
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )

                            ) + fadeIn(animationSpec = tween(300))
                        }
                    ) {
                        Quran(navController)
                    }


                    composable(
                        "Verses",
                        exitTransition = {_,_ ->
                            slideOutHorizontally(
                                targetOffsetX = {-300},
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )

                            ) + fadeOut(animationSpec = tween(300))
                        },
                        popEnterTransition = {_,_ ->
                            slideInHorizontally(
                                initialOffsetX = {-300},
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )

                            ) + fadeIn(animationSpec = tween(300))
                        }
                    ) {
                        Verses(navController)
                    }

                     composable(
                            "Player",
                            exitTransition = {_,_ ->
                                slideOutHorizontally(
                                    targetOffsetX = {-300},
                                    animationSpec = tween(
                                        durationMillis = 300,
                                        easing = FastOutSlowInEasing
                                    )

                                ) + fadeOut(animationSpec = tween(300))
                            },
                            popEnterTransition = {_,_ ->
                                slideInHorizontally(
                                    initialOffsetX = {-300},
                                    animationSpec = tween(
                                        durationMillis = 300,
                                        easing = FastOutSlowInEasing
                                    )

                                ) + fadeIn(animationSpec = tween(300))
                            }
                        ) {
                         Player(navController)
                        }



                }

            }
        )
    }


}


@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(

        // set background color
        backgroundColor = Color(0xff6162F5)) {

        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        // Bottom nav items we declared
        Constants.BottomNavItems.forEach { navItem ->

            // Place the bottom nav items
            BottomNavigationItem(



                // it currentRoute is equal then its selected route
                selected = currentRoute == navItem.route,

                // navigate on click
                onClick = {
                    navController.navigate(navItem.route)
                },

                // Icon of navItem
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label , tint = Color.White)
                },

                // label
                label = {
                    Text(text = navItem.label, color = Color.White)
                },
                alwaysShowLabel = false,


            )
        }

    }
}