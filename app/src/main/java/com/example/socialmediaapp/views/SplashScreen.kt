package com.example.socialmediaapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.socialmediaapp.R
import com.example.socialmediaapp.navigation.DestinationScreen
import com.example.socialmediaapp.utils.navigateTo
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.syncphere),
            contentDescription = null,
            modifier = Modifier
                .width(200.dp)
        )
    }

    LaunchedEffect(key1 = true) {
        delay(3000)
        navigateTo(navController, DestinationScreen.Login)
    }
}
