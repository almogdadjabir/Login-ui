package com.almogdadjabir.com.view

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.almogdadjabir.com.R
import com.almogdadjabir.com.model.remote.verses.Verses
import com.almogdadjabir.com.ui.theme.Kitab
import com.almogdadjabir.com.ui.theme.uthman
import com.almogdadjabir.com.view_model.QuranParentViewModel
import com.almogdadjabir.com.view_model.VersesViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.foundation.Image
import androidx.core.text.HtmlCompat
import com.almogdadjabir.com.model.remote.translations.Translations
import com.almogdadjabir.com.view_model.TranslationViewModel


@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("UnrememberedGetBackStackEntry")
@ExperimentalCoilApi
@Composable
fun Verses(
    navHostController: NavHostController,
    viewModel: QuranParentViewModel = hiltViewModel(LocalContext.current as ViewModelStoreOwner),
    versesViewModel: VersesViewModel = hiltViewModel(),
    translationViewModel: TranslationViewModel = hiltViewModel(),
) {
    val chaptrs by viewModel.chaptrs.observeAsState()



    Log.e("id", "${chaptrs?.id!!}")

    versesViewModel.getAllVerses(chaptrs?.id!!)
    translationViewModel.getAllTranslations(chaptrs?.id!!)


    val list by versesViewModel.allVerses.observeAsState()
    val translation_list by translationViewModel.allTranslations.observeAsState()
    translation_list?.let {
        QuranVersesDrawUI(chaptrs?.name_simple!! + " \n" + chaptrs?.name_arabic!!, list!!, it, navHostController) } ?:
        Text(
            text = "Wait",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

    }

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalCoilApi
@Composable
fun QuranVersesDrawUI(chaptr: String, list: List<Verses>, tran_list: List<Translations>, navHostController: NavHostController) {
    // set background color
    var count = 1
    var i = 0
   // var tlist: Translations? = null

    Log.d("lol", "DrawUI: List Size =  ${list.size}")
    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = Color(0xfff1f0f7)
    )

    Column() {


        LazyColumn(
            Modifier
                .fillMaxWidth()
                .background(color = Color(0xfff1f0f7))
                .padding(bottom = 30.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {

                Text(modifier = Modifier
                    .padding(top = 30.dp, start = 30.dp)
                    .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF3a354b),
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp, fontFamily = FontFamily.Serif
                            )
                        ) {
                            append("${chaptr} \n")
                        }
                    }
                )
                Text(modifier = Modifier
                    .padding(top = 5.dp, start = 5.dp)
                    .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFFfc6b68),
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp, fontFamily = uthman
                            )
                        ) {
                            append("بسم الله الرحمن الرحيم")
                        }
                    }
                )

            }

            items(list) { item ->

                QuranVersesDrawCard(item , tran_list[i], count)
                count++
                i++
            }

        }
    }
}


@ExperimentalCoilApi
@Composable
fun QuranVersesDrawCard(
    item: Verses, tlist: Translations, count: Int) {
    val countr = item.verse_key!!.split(":").toTypedArray()

    //QuranVersesWriteText(item.text_indopak!!)
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl ) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = item.text_uthmani!!,
                    fontSize = 18.sp,
                    color = Color(0xFF3a354b),
                    textAlign = TextAlign.Start,
                    fontFamily = Kitab,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = 10,
                    modifier = Modifier.weight(3f)
                )

                Box(modifier = Modifier.weight(1f)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic__327359_ornament_decoration_islamic_arabic_pattern_icon),
                        contentDescription = null,
                        Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .align(Alignment.Center)
                    )

                    Text(
                        text = countr[1],
                        Modifier
                            .clip(CircleShape)
                            .align(Alignment.Center),
                        fontSize = 14.sp,
                        color = Color(0xFFffffff),
                        fontFamily = Kitab,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

            }
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    Text(
                        text = "${HtmlCompat.fromHtml(tlist.text!!, HtmlCompat.FROM_HTML_MODE_COMPACT)}",
                        fontSize = 18.sp,
                        color = Color(0xFF3a354b),
                        textAlign = TextAlign.Start,
                        fontFamily = Kitab,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 10,
                        modifier = Modifier.weight(3f)
                    )

                }
            }
        }
    }
}

@Composable
fun QuranVersesWriteText(title: String = "From Service Provider") {
    Column(
        Modifier
            .fillMaxSize()
            .height(150.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(2f), contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.fillMaxSize(),
                text = title,
                color = Color(0xFF3a354b),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default, maxLines = 3, overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

