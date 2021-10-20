package com.almogdadjabir.com.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.almogdadjabir.com.model.remote.Articles
import com.almogdadjabir.com.view_model.HomeViewModel
import com.almogdadjabir.com.view_model.ParentViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.M)
@ExperimentalCoilApi
@Composable
fun HomeNews(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),

) {
    val list by homeViewModel.allNews.observeAsState()
    list?.let { DrawUI(it, navHostController) } ?: Text(
        text = "Wait",
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@ExperimentalCoilApi
@Composable
fun DrawUI(list: List<Articles>, navHostController: NavHostController) {
    // set background color
    Log.d("lol", "DrawUI: List Size =  ${list.size}")
    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = Color(0xfff1f0f7)
    )
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .background(color = Color(0xfff1f0f7)),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
                    append("Latest \n")
                    append("News")
                }
            })

            Text(text = String(Character.toChars(0x1F680))+" By Moe alsadig ~LOL BOY"+String(Character.toChars(0x1F609)), fontWeight = FontWeight.Light, fontSize = 14.sp, modifier = Modifier.padding(bottom = 15.dp, start = 30.dp).fillMaxWidth(),textAlign = TextAlign.Start,)
        }
        items(list) { item ->
            DrawCard(item, navHostController)
        }
    }
}


@ExperimentalCoilApi
@Composable
fun DrawCard(
    item: Articles,
    navHostController: NavHostController,
    parentViewModel: ParentViewModel = hiltViewModel(LocalContext.current as ViewModelStoreOwner)
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(15.dp)
            .clickable {
                parentViewModel.getArticlesObject(item = item) // Sending Data
                navHostController.navigate("Details")
            }, backgroundColor = Color(0xffffffff)
    ) {
        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .weight(2f)
                    .padding(5.dp)
            ) {
                if (item.author != null) {
                    WriteText(item.author, item.title!!)
                } else {
                    if (item.title != null)
                        WriteText(title = item.title)
                    else
                        WriteText()
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                val painter = rememberImagePainter(data = item.urlToImage, builder = {
                    transformations(RoundedCornersTransformation(10f))
                    error(R.drawable.ic__animal_ears_hat_layer_photo_icon)
                })
                Image(painter = painter, contentDescription = "Just Image")
                val state = painter.state
                if ((state is ImagePainter.State.Loading) || (state is ImagePainter.State.Error)) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun WriteText(author: String = "No Data", title: String = "From Service Provider") {
    val map = mapOf(2 to Color.Blue, 1 to Color.Magenta)
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Surface(
                shape = CircleShape,
                color = map[Random.nextInt(1, 3)]!!,

                ) {
                Text(
                    text = author, color = Color(0xFFffffff), modifier = Modifier
                        .padding(top = 3.dp, start = 5.dp,bottom = 3.dp,end = 5.dp), fontWeight = FontWeight.SemiBold
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
                textAlign = TextAlign.End,
                fontFamily = FontFamily.Default, maxLines = 3, overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}