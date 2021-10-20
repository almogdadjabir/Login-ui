package com.almogdadjabir.com.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.almogdadjabir.com.R
import com.almogdadjabir.com.view_model.ParentViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("UnrememberedGetBackStackEntry")
@ExperimentalCoilApi
@Composable
fun SingIn(
    navHostController: NavHostController,
    viewModel: ParentViewModel = hiltViewModel(LocalContext.current as ViewModelStoreOwner)
) {
    var username by remember {
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }

    var isPasswordVisable by remember {
        mutableStateOf(false)
    }

    val isFormValid by derivedStateOf {
        username.isNotBlank() && password.length > 1
    }

    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colors.primary
    )

    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
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
                        .padding(32.dp)) {
                    Text(text = "Hello Again!", fontWeight = FontWeight.Bold, fontSize = 24.sp,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Welcome back you've \n been missed!", fontWeight = FontWeight.Light, fontSize = 20.sp,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
                    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedTextField(
                            value = username,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { username = it },
                            label = {Text(text = "Username")},
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            trailingIcon = {
                                if (username.isNotBlank())
                                    IconButton(onClick = {username = ""}) {
                                        Icon(imageVector = Icons.Filled.Clear, contentDescription = "")

                                    }
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = password,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { password = it },
                            singleLine = true,
                            label = {Text(text = "Password")},
                            shape = RoundedCornerShape(8.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                            visualTransformation = if (isPasswordVisable) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = { isPasswordVisable = !isPasswordVisable }) {
                                    Icon(imageVector = if(isPasswordVisable) Icons.Default.Visibility else Icons.Default.VisibilityOff ,
                                        contentDescription = "")
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                navHostController.navigate("Home")
                            },
                            enabled = isFormValid,
                            modifier = Modifier.fillMaxWidth() .defaultMinSize(minHeight = 45.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(text = "Log In")
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            TextButton(onClick = {
                                navHostController.navigate("SingUp")

                            }) {
                                Text(text = "Sing Up")
                            }

                            TextButton(onClick = {}) {
                                Text(text = "Forget Password?", color = Color.Gray)

                            }
                        }


                    }
                }


            }
        }

    }
}

