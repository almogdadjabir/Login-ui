package com.almogdadjabir.com.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.almogdadjabir.com.R
import com.almogdadjabir.com.model.remote.Chapters
import com.almogdadjabir.com.ui.theme.uthman
import com.almogdadjabir.com.view_model.QuranParentViewModel
import com.almogdadjabir.com.view_model.QuranViewModel
import com.almogdadjabir.com.view_model.TranslationViewModel
import com.almogdadjabir.com.view_model.VersesViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer


@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("UnrememberedGetBackStackEntry")
@ExperimentalCoilApi
@Composable
fun Player(
    navHostController: NavHostController,
    viewModel: QuranParentViewModel = hiltViewModel(LocalContext.current as ViewModelStoreOwner),
    versesViewModel: VersesViewModel = hiltViewModel(),
    translationViewModel: TranslationViewModel = hiltViewModel(),
    homeViewModel: QuranViewModel = hiltViewModel(),


    ) {
    val (isChecked, setChecked) = remember { mutableStateOf(false) }


    val context = LocalContext.current
    //determineCurrentlyPlayingItem(context, "file:///android_asset/" +
    //        "bismillah.mp3")

    val navController = rememberAnimatedNavController()

    Scaffold(
        topBar = {},
        drawerContent = {/**/ },
        bottomBar = {/**/ },
        floatingActionButton = {
            //MyFloatingActionButton()
        },
        snackbarHost = {/**/ },
        content = {

            Column(
                Modifier
                    .background(color = Color(0xfff1f0f7))
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val lists = homeViewModel.allChapter.observeAsState()

                val systemUiController = rememberSystemUiController()

                systemUiController.setSystemBarsColor(
                    color = Color(0xfff1f0f7)
                )

                val painter = rememberImagePainter(data = "", builder = {
                    transformations(RoundedCornersTransformation(10f))
                    error(R.drawable.ic_islamic)
                })
                val painter2 = rememberImagePainter(data = "", builder = {
                    transformations(RoundedCornersTransformation(10f))
                    error(R.drawable.ic__327361_arabic_lamp_light_bulb_lightbulb_icon)
                })



                Box(
                    modifier = Modifier
                        .weight(1f),

                ) {
                    Image(
                        painter = painter,
                        contentDescription = "Just Image",
                        modifier = Modifier
                            .padding(0.dp)
                            .background(Color(0xFFE3DAC9))
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop,

                    )

                    Box (
                        Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(
                                Color(0x7E000000),
                                RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp)
                            ),
                        )
                    {
                        Row(Modifier.fillMaxWidth()) {
                            Column(
                                Modifier
                                    .weight(2f)
                                    .padding(16.dp)
                            ) {

                                Text(
                                    text = "Ayatul Kursi",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFFf3ffff)
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                Text(
                                    text = "اية الكرسي",
                                    fontSize = 16.sp,
                                    fontFamily = uthman,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xD7F3FFFF)
                                )

                            }

//                            Button(
//                                onClick = {
//                                    determineCurrentlyPlayingItem(context, "file:///android_asset/" +
//                                            "ayatalkursi.mp3")
//                                },
//                                colors = ButtonDefaults.buttonColors(
//                                    backgroundColor = Color(0x0),
//                                    contentColor = Color(0xFFffffff)
//                                ),
//                                elevation = elevation(
//                                    defaultElevation = 0.dp,
//                                    pressedElevation = 1.dp
//                                ),
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .weight(1f)
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.PlayArrow,
//                                    contentDescription = "",
//                                    modifier = Modifier.height(35.dp).width(35.dp)
//                                )
//                            }

                            FavoriteButton(
                                isChecked = isChecked,
                                onClick = { setChecked(!isChecked) }
                            )
                                determineCurrentlyPlayingItem(context,
                                    "file:///android_asset/" + "ayatalkursi.mp3",
                                    isChecked)



                        }
                    }

                }
                Column(
                    Modifier
                        .background(Color.White)
                        .defaultMinSize(minHeight = 300.dp)
                        .padding(16.dp)
                        .weight(2f)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Popular Searches",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(bottom = 16.dp, top = 16.dp)
                            .fillMaxWidth(),textAlign = TextAlign.Start,
                        color = Color(0xFF221945)
                    )
                    Spacer(modifier = Modifier.height(10.dp))


                    LazyRow(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight()
                    ) {
                        item() {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(Alignment.CenterVertically)
                                    .padding(16.dp),
                                shape = RoundedCornerShape(6.dp),
                                backgroundColor = Color(0xffffffff),


                                ) {

                                Row(
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painter2, contentDescription = "Just Image",
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(60.dp)
                                    )

                                    Column(
                                        Modifier.padding(16.dp)
                                    ) {
                                        Text(
                                            text = "Ayatul Kursi",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF1f1846)
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        Text(
                                            text = "اية الكرسي",
                                            fontSize = 16.sp,
                                            fontFamily = uthman,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF615F68)
                                        )
                                    }
                                }
                            } // end of card

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(Alignment.CenterVertically)
                                    .padding(16.dp),
                                backgroundColor = Color(0xffffffff),
                                shape = RoundedCornerShape(6.dp)

                            ) {

                                Row(
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painter2, contentDescription = "Just Image",
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(60.dp)
                                    )

                                    Column(
                                        Modifier.padding(16.dp)

                                    ) {
                                        Text(
                                            text = "Surah Yaseen",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF1f1846)
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        Text(
                                            text = "سورة يس",
                                            fontSize = 16.sp,
                                            fontFamily = uthman,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF615F68)
                                        )
                                    }
                                }
                            } // end of card


                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(Alignment.CenterVertically)
                                    .padding(16.dp),
                                 backgroundColor = Color(0xffffffff),
                                shape = RoundedCornerShape(6.dp)

                            ) {

                                Row() {
                                    Image(
                                        painter = painter2, contentDescription = "Just Image",
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(60.dp)
                                    )

                                    Column(
                                        Modifier.padding(16.dp)

                                    ) {
                                        Text(
                                            text = "Surah Al Mulk",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF1f1846)
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        Text(
                                            text = "سورة الملك",
                                            fontSize = 16.sp,
                                            fontFamily = uthman,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF615F68)
                                        )
                                    }
                                }
                            } // end of card



                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(Alignment.CenterVertically)
                                    .padding(16.dp),
                                backgroundColor = Color(0xffffffff),
                                shape = RoundedCornerShape(6.dp)

                            ) {

                                Row() {
                                    Image(
                                        painter = painter2, contentDescription = "Just Image",
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(60.dp)
                                    )

                                    Column(
                                        Modifier.padding(16.dp)
                                    ) {
                                        Text(
                                            text = "Surah Ar-Rahman",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF1f1846)
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        Text(
                                            text = "سورة الرحمن",
                                            fontSize = 16.sp,
                                            fontFamily = uthman,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF615F68)
                                        )
                                    }
                                }
                            } // end of card


                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(Alignment.CenterVertically)
                                    .padding(16.dp),
                                backgroundColor = Color(0xffffffff),
                                shape = RoundedCornerShape(6.dp)

                            ) {

                                Row() {
                                    Image(
                                        painter = painter2, contentDescription = "Just Image",
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(60.dp)
                                    )

                                    Column(
                                        Modifier.padding(16.dp)
                                    ) {
                                        Text(
                                            text = "Surah Al Waqi'ah",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF1f1846)
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        Text(
                                            text = "سورة الواقعة",
                                            fontSize = 16.sp,
                                            fontFamily = uthman,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF615F68)
                                        )
                                    }
                                }
                            } // end of card




                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(Alignment.CenterVertically)
                                    .padding(16.dp),
                                backgroundColor = Color(0xffffffff),
                                shape = RoundedCornerShape(6.dp)

                            ) {

                                Row() {
                                    Image(
                                        painter = painter2, contentDescription = "Just Image",
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(60.dp)
                                    )

                                    Column(
                                        Modifier.padding(16.dp)
                                    ) {
                                        Text(
                                            text = "Surah Al khaf",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF1f1846)
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        Text(
                                            text = "سورة الكهف",
                                            fontSize = 16.sp,
                                            fontFamily = uthman,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF615F68)
                                        )
                                    }
                                }
                            } // end of card



                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(Alignment.CenterVertically)
                                    .padding(16.dp),
                                backgroundColor = Color(0xffffffff),
                                shape = RoundedCornerShape(6.dp)

                            ) {

                                Row() {
                                    Image(
                                        painter = painter2, contentDescription = "Just Image",
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(60.dp)
                                    )

                                    Column(
                                        Modifier.padding(16.dp)
                                    ) {
                                        Text(
                                            text = "Surah Al Muzzammil",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF1f1846)
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        Text(
                                            text = "سورة المزمل",
                                            fontSize = 16.sp,
                                            fontFamily = uthman,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF615F68)
                                        )
                                    }
                                }
                            } // end of card

                        }

                    }
                    // end of LazyRow

                    LazyColumn(
                        Modifier
                            .weight(2f)
                            .background(color = Color(0xFFfdfdfd))
                            .padding(bottom = 30.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Log.e("listprint","${lists}")

                        lists.value?.let {
                            items(it) { item ->
                                QuranDrawCard(item)

                            }
                        }
                    }

                }
            }
        }
  )

}

    private fun determineCurrentlyPlayingItem(context:Context, videoUri: String, isChecked: Boolean){
        Log.d("isChecked","call")

        val player = SimpleExoPlayer.Builder(context).build()
        val mediaItem: MediaItem = MediaItem.fromUri(videoUri)
        player.setMediaItem(mediaItem)
        player.prepare()

        Log.d("isChecked","${isChecked}")
        Log.d(" currentTimeline","${ player.currentTimeline}")
        if(isChecked) {
            player.stop()
            //Toast.makeText(context,"Start",Toast.LENGTH_LONG).show()

        }else{
            //Toast.makeText(context,"stop",Toast.LENGTH_LONG).show()
            player.stop()

        }







    }



@Composable
fun topBar(){
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
            .background(color = Color(0xFF3da2fd))
            .padding(16.dp)) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0x34FFFFFF),
                    contentColor = Color.White
                ),
                elevation = elevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 2.dp
                ),
                modifier = Modifier
                    .height(55.dp)
                    .width(55.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
             }
        }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun QuranDrawCard(
    item: Chapters
) {
    val painter = rememberImagePainter(data = "", builder = {
        transformations(RoundedCornersTransformation(10f))
        error(R.drawable.ic__327368_quran_book_holy_education_learning_icon)
    })

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight(Alignment.CenterVertically),
        backgroundColor = Color(0xffffffff),
        shape = RoundedCornerShape(6.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {

            Column(
                Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Image(
                    painter = painter, contentDescription = "Just Image", modifier = Modifier
                        .height(60.dp)
                        .width(60.dp)
                )
            }

            Column(
                Modifier
                    .weight(2f)
                    .padding(16.dp)
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.name_simple,
                    color = Color(0xFF3a354b),
                    textAlign = TextAlign.Start,
                    fontFamily = uthman,
                    maxLines = 3,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.ExtraBold

                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.name_arabic,
                    color = Color(0xFFfc6b68),
                    textAlign = TextAlign.Start,
                    fontFamily = uthman,
                    maxLines = 3,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.ExtraBold
                )
            }

        }
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun FavoriteButton(
    isChecked: Boolean,
    onClick: () -> Unit,
) {
    IconToggleButton(
        checked = isChecked,
        onCheckedChange = { onClick() },
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        val transition = updateTransition(isChecked, label = "Checked indicator")

        val tint by transition.animateColor(
            label = "Tint"
        ) { isChecked ->
            if (isChecked) Color.White else Color.White
        }

        val size by transition.animateDp(
            transitionSpec = {
                if (false isTransitioningTo true) {
                    keyframes {
                        durationMillis = 250
                        30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                        35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                        40.dp at 75 // ms
                        35.dp at 150 // ms
                    }
                } else {
                    spring(stiffness = Spring.StiffnessVeryLow)
                }
            },
            label = "Size"
        ) { 30.dp }

        Icon(
            imageVector = if (isChecked) Icons.Filled.Pause else Icons.Filled.PlayArrow,
            contentDescription = null,
            tint = tint,
            //modifier = Modifier.height(35.dp).width(35.dp)
            modifier = Modifier.size(size)
        )
    }
}
