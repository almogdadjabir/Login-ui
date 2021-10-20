package com.almogdadjabir.com.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.almogdadjabir.com.R
import com.almogdadjabir.com.model.remote.Chapters
import com.almogdadjabir.com.ui.theme.Kitab
import com.almogdadjabir.com.ui.theme.Poppins_regular
import com.almogdadjabir.com.ui.theme.uthman
import com.almogdadjabir.com.view_model.QuranParentViewModel
import com.almogdadjabir.com.view_model.QuranViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.M)
@ExperimentalCoilApi
@Composable
fun Quran(
    navHostController: NavHostController,
    homeViewModel: QuranViewModel = hiltViewModel(),

    ) {

    Scaffold(
        topBar = {/**/ },
        drawerContent = {/**/ },
        bottomBar = {/**/ },
        floatingActionButton = {
            //MyFloatingActionButton()

        },
        snackbarHost = {/**/ },
        content = {
            val list by homeViewModel.allChapter.observeAsState()
            list?.let { QuranDrawUI(it, navHostController) } ?: Text(
                text = "Wait",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        modifier = Modifier.padding(bottom = 50.dp)
    )



}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalCoilApi
@Composable
fun QuranDrawUI(list: List<Chapters>, navHostController: NavHostController) {
    // set background color
    Log.d("lol", "DrawUI: List Size =  ${list.size}")
    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = Color(0xfff1f0f7)
    )


        LazyVerticalGrid(
            // fix the item in one row to be 2.
            cells = GridCells.Fixed(3),

            contentPadding = PaddingValues(8.dp),
    ) {
        item {
            Text(modifier = Modifier
                .padding(top = 30.dp, start = 30.dp)
                .fillMaxSize(), text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF3a354b),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp, fontFamily = FontFamily.Serif
                    )
                ) {
                    append("The Hole Quran \n")
                }
            })

            //Text(text = String(Character.toChars(0x1F680))+" By Moe alsadig ~LOL BOY"+String(Character.toChars(0x1F609)), fontWeight = FontWeight.Light, fontSize = 14.sp, modifier = Modifier.padding(bottom = 15.dp, start = 30.dp).fillMaxWidth(),textAlign = TextAlign.Start,)
        }
        items(list) { item ->
            QuranDrawCard(item, navHostController)
        }

    }
}


@ExperimentalCoilApi
@Composable
fun QuranDrawCard(
    item: Chapters,
    navHostController: NavHostController,
    parentViewModel: QuranParentViewModel = hiltViewModel(LocalContext.current as ViewModelStoreOwner)
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(15.dp)
            .clickable {
                parentViewModel.getChaptrsObject(item = item) // Sending Data
                navHostController.navigate("Verses")
            }, backgroundColor = Color(0xffffffff)
    ) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                val painter = rememberImagePainter(data = "", builder = {
                    transformations(RoundedCornersTransformation(10f))
                    error(R.drawable.ic__327368_quran_book_holy_education_learning_icon)
                })
                Image(painter = painter, contentDescription = "Just Image")
                val state = painter.state
                if ((state is ImagePainter.State.Loading) || (state is ImagePainter.State.Error)) {
                    // CircularProgressIndicator()
                }
            }
            Box(
                Modifier
                    .fillMaxHeight()
                    .weight(2f)
                    .padding(5.dp)
            ) {
                if (item.name_simple != null) {
                    QuranWriteText(item.name_simple, item.name_arabic!!)
                } else {
                    if (item.name_arabic != null)
                        QuranWriteText(title = item.name_arabic)
                    else
                        QuranWriteText()
                }
            }
            Spacer(modifier = Modifier.width(10.dp))

        }
    }
}

@Composable
fun QuranWriteText(author: String = "No Data", title: String = "From Service Provider") {
    val map = mapOf(2 to Color.Blue, 1 to Color.Magenta)
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Surface(
                shape = CircleShape,
                //color = Color(0xFFfc6b68),
                modifier = Modifier.fillMaxWidth()

                ) {
                Text(
                    text = author,
                    color = Color(0xFFfc6b68),
                    modifier = Modifier
                        .padding(top = 3.dp, start = 5.dp,bottom = 3.dp,end = 5.dp),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontFamily = Poppins_regular,

                    )
            }
        }

        Box(
            modifier = Modifier
                .weight(2f), contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.fillMaxSize(),
                text = title,
                color = Color(0xFF3a354b),
                textAlign = TextAlign.Center,
                fontFamily = uthman,
                maxLines = 3,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}


@Composable
fun MyFloatingActionButton() {

    FloatingActionButton(
        onClick = {},
    ) {
        Icon(imageVector = Icons.Default.Home,
            contentDescription = "")

    }
}