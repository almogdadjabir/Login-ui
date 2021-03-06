package com.almogdadjabir.com.view.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.Navigator

@Composable
fun BackButton() {
    Icon(
        Icons.Default.ArrowBack,
        contentDescription = "back",
        modifier = Modifier
            .padding(top = 8.dp)
            .clip(CircleShape)
            .clickable {
               // navController.navigateUp()
            }
            .padding(16.dp)
    )
}