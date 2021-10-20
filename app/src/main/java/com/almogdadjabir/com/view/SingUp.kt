package com.almogdadjabir.com.view

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.almogdadjabir.com.R
import com.almogdadjabir.com.view_model.ParentViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.util.*

var rnds = 0 // generated random from 0 to 10 included

@SuppressLint("UnrememberedGetBackStackEntry")
@ExperimentalCoilApi
@Composable
fun SingUp(
    navHostController: NavHostController,
    viewModel: ParentViewModel = hiltViewModel(LocalContext.current as ViewModelStoreOwner)
) {

    val emoji = arrayOf<String>(
        "be a part of our family " + String(Character.toChars(0x1F680)),
        "be a part of our family " + String(Character.toChars(0x1F60D)),
        "be a part of our family " + String(Character.toChars(0x1F63A)),
        "be a part of our family " + String(Character.toChars(0x270A)),
        "be a part of our family " + String(Character.toChars(0x1F60F)),
        "be a part of our family " + String(Character.toChars(0x1F6A7)),
        "be a part of our family " + String(Character.toChars(0x2665)),
        "be a part of our family " + String(Character.toChars(0x1F631)),
        "be a part of our family " + String(Character.toChars(0x1F33A)),
        "be a part of our family " + String(Character.toChars(0x1F344)),
        "be a part of our family " + String(Character.toChars(0x1F36A)),

    )


    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isPasswordVisable by remember {
        mutableStateOf(false)
    }

    val isFormValid by derivedStateOf {
        username.isNotBlank() && password.length > 7
    }

    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colors.primary
    )

    myTimer()

    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic__animal_ears_hat_layer_photo_icon),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(180.dp),
//                colorFilter = ColorFilter.tint(Color.White)
            )
            Card(
                Modifier
                    .weight(2f)
                    .padding(8.dp),
                shape = RoundedCornerShape(32.dp)

            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(
                        text = "Welcome to Our Lab",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    //for (emoj in emoji) {
                        //Thread.sleep(5_000)
                        // wait for 1 second
                        Text(
                            text = emoji.get(rnds),
                            fontWeight = FontWeight.Light,
                            fontSize = 18.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    //}



                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedTextField(
                            value = username,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { username = it },
                            label = { Text(text = "Username") },
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            trailingIcon = {
                                if (username.isNotBlank())
                                    IconButton(onClick = { username = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = ""
                                        )

                                    }
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = password,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { password = it },
                            singleLine = true,
                            label = { Text(text = "Password") },
                            shape = RoundedCornerShape(8.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            visualTransformation = if (isPasswordVisable) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = { isPasswordVisable = !isPasswordVisable }) {
                                    Icon(
                                        imageVector = if (isPasswordVisable) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = ""
                                    )
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = password,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { password = it },
                            singleLine = true,
                            label = { Text(text = "Password") },
                            shape = RoundedCornerShape(8.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            visualTransformation = if (isPasswordVisable) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = { isPasswordVisable = !isPasswordVisable }) {
                                    Icon(
                                        imageVector = if (isPasswordVisable) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = ""
                                    )
                                }
                            }
                        )


                        Spacer(modifier = Modifier.weight(1f))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(onClick = {
                                navHostController.navigate("SingIn")

                            },
                                colors= ButtonDefaults.buttonColors(backgroundColor = Color(0xffeeeeee)),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "",
                                    tint = Color(0xff6e6e6e)


                                )
                            }

                            Button(
                                onClick = {
                                    navHostController.navigate("Home")
                                },
                                enabled = isFormValid,
                                modifier = Modifier
                                    .defaultMinSize(minHeight = 45.dp, minWidth = 100.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(text = "Continuer")
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = ""
                                )
                            }
                        }


                    }
                }


            }
        }

    }

}

fun  myTimer(){

    val timer = object: CountDownTimer(50000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            rnds = (0..10).random() // generated random from 0 to 10 included

        }

        override fun onFinish() {
            rnds = 1
        }
    }
    timer.start()
}
